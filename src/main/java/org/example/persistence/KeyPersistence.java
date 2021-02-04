package org.example.persistence;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/04
 */
import org.example.tx.XAObjectStore;

import java.io.IOException;

/**
 * Abstract interface of store data
 *
 * @Description: KeyStorageService
 * @author graysonzhang
 * @date 2020-06-18 14:17:00
 *
 */
public interface KeyPersistence extends XAObjectStore {

    void save(String userId, String keyAddress, String keyName, String encryptKey)
            throws IOException;

    /*
    EncryptKeyInfo getEncryptKeyInfoByUserIdAndKeyAddress(String userId, String address) throws KeyMgrException;

    List<EncryptKeyInfo> getEncryptKeyInfoByUserId(String userId) throws KeyMgrException;

    boolean updateKeyName(String userId, String address, String newKeyName) throws KeyMgrException;

    boolean updateEncrypt(String userId, String keyAddress, String newEncryptKey, String newPassword) throws KeyMgrException;

    boolean removeEncryptKey(String userId, String keyAddress) throws KeyMgrException;

    List<EncryptKeyInfo> getChildKeys(String userId, String parentAddress) throws KeyMgrException;

     */
}
