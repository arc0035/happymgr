package org.example;

import com.atomikos.icatch.jta.UserTransactionManager;
import org.example.config.EccTypeEnums;
import org.example.config.KeyMgrConfig;
import org.example.key.EncodableKey;
import org.example.key.EncodableKeyFactory;
import org.example.model.PkeyInfoVO;
import org.example.persistence.KeyPersistence;
import org.example.persistence.PwdPersistence;
import org.fisco.bcos.sdk.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.crypto.keypair.ECDSAKeyPair;
import org.fisco.bcos.sdk.crypto.keypair.SM2KeyPair;

import javax.transaction.Transaction;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/04
 */

public class KeyManagerService {

    private KeyMgrConfig config;
    private EncodableKeyFactory keyFactory;
    private UserTransactionManager userTransactionManager;
    private KeyPersistence keyPersistence;
    private PwdPersistence pwdPersistence;

    public KeyManagerService(KeyMgrConfig config, KeyPersistence keyPersistence, PwdPersistence pwdPersistence){
        this.config = config;
        this.keyPersistence = keyPersistence;
        this.pwdPersistence = pwdPersistence;
        this.keyFactory = new EncodableKeyFactory();
        this.userTransactionManager = new UserTransactionManager();
    }
    /**
     * 从私钥明文加密托管，其中数据保存到
     * 无论是C端用户，还是面向B端的机构，都可以采用这个模式。
     */
    public PkeyInfoVO importPrivateKey(String userId, String password, String privateKey, String keyName) throws Exception{
        /**
         * 1、加密私钥，并获取地址
         */
        String encodedString = getEncodedKey(privateKey);
        String address = getCryptoKeyPair(privateKey).getAddress();
        /**
         * 2、密文和密码按分布式事务存储
         * */
        //初始化事务
        Transaction tx = startTx();
        try{
            // 执行双库存储逻辑
            this.keyPersistence.save(userId, address, keyName, encodedString);
            this.pwdPersistence.save(userId, address, password);
            // 提交
            tx.commit();
        }
        catch (Exception ex){
            tx.rollback();
        }

        /**
         * 3. 返回结果
         */
        PkeyInfoVO r = new PkeyInfoVO();
        r.setKeyAddress(address);
        r.setKeyName(keyName);
        return r;
    }

    private CryptoKeyPair getCryptoKeyPair(String privateKey) {
        if(this.config.getEccType() == EccTypeEnums.SECP256K1){
            return new ECDSAKeyPair().createKeyPair(privateKey);
        }
        else{
            return new SM2KeyPair().createKeyPair(privateKey);
        }
    }

    private String getEncodedKey(String privateKey) throws IOException {
        EncodableKey key = this.keyFactory.buildEncodableKey(privateKey, this.config.getKeyEncodeType(), this.config.getEccType());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        key.encode(baos);
        byte[] encoded = baos.toByteArray();
        return key.toString(new ByteArrayInputStream(encoded));
    }

    /**
     * 创建事务，并绑定两个资源管理器
     */
    private Transaction startTx() throws Exception{
        this.userTransactionManager.begin();
        Transaction tx = this.userTransactionManager.getTransaction();
        tx.enlistResource(this.keyPersistence.getXAResource());
        tx.enlistResource(this.pwdPersistence.getXAResource());
        return tx;
    }


}

