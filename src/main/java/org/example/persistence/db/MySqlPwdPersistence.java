package org.example.persistence.db;

import org.example.config.KeyMgrConfig;
import org.example.persistence.KeyPersistence;
import org.example.persistence.PwdPersistence;

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
public class MySqlPwdPersistence implements PwdPersistence {
    private static final String INSERT = "INSERT INTO key_pwds_info(key_address, user_id, key_pwd) VALUES(?,?,?)";

    private XAConnection xaConnection;
    private XAResource xaResource;

    public MySqlPwdPersistence(XAConnection xaConnection) throws IOException {
        try{
            this.xaConnection = xaConnection;
            this.xaResource = xaConnection.getXAResource();
        }
        catch (Exception ex){
            throw new IOException(ex);
        }
    }

    @Override
    public void save(String userId, String keyAddress, String pwd) throws IOException {
        try{
            Connection connection = this.xaConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setString(1, keyAddress);
            ps.setString(2, userId);
            ps.setString(3, pwd);
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
