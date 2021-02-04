package org.example.persistence;

import org.example.tx.XAObjectStore;

import java.io.IOException;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/04
 */
public interface PwdPersistence extends XAObjectStore {
    void save(String userId, String keyAddress, String password)
            throws IOException;
}
