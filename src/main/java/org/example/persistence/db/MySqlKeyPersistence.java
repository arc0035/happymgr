package org.example.persistence.db;

import org.example.config.KeyMgrConfig;
import org.example.persistence.KeyPersistence;

import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/04
 */
public class MySqlKeyPersistence implements KeyPersistence {
    private static final String INSERT = "INSERT INTO encrypt_keys_info(encrypt_key,key_address,key_name,user_id,ecc_type,enc_type) VALUES(?,?,?,?,?,?)";

    private XAConnection xaConnection;
    private XAResource xaResource;
    private KeyMgrConfig config;

    public MySqlKeyPersistence(XAConnection xaConnection, KeyMgrConfig config) throws IOException {
        try{
            this.xaConnection = xaConnection;
            this.xaResource = xaConnection.getXAResource();
            this.config = config;
        }
        catch (Exception ex){
            throw new IOException(ex);
        }
    }

    @Override
    public void save(String userId, String keyAddress, String keyName, String encryptKey) throws IOException {
        try{
            Connection connection = this.xaConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setString(1, encryptKey);
            ps.setString(2, keyAddress);
            ps.setString(3, keyName);
            ps.setString(4, userId);
            ps.setString(5, this.config.getEccType().name());
            ps.setString(6, this.config.getKeyEncodeType().name());
            ps.executeUpdate();
        }
        catch (Exception ex){
            throw new IOException(ex);
        }
    }

    @Override
    public XAResource getXAResource() {
        return this.xaResource;
    }
}
