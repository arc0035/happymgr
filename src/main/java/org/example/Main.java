package org.example;

import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlXAConnection;
import org.example.config.KeyMgrConfig;
import org.example.persistence.KeyPersistence;
import org.example.persistence.db.MySqlKeyPersistence;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/04
 */
public class Main {

    public static void main(String[] args) throws Exception{
        String url = "jdbc:mysql://127.0.0.1:3306/pkey_mgr?autoReconnect=true&characterEncoding=utf8&useSSL=false&serverTimezone=GMT%2b8";
        String user = "root";
        String pwd = "123456";

        Connection conn = DriverManager.getConnection(url, user, pwd);
        KeyPersistence kp = new MySqlKeyPersistence(new MysqlXAConnection((JdbcConnection) conn, true), new KeyMgrConfig());
        System.out.println("Complete");


    }

}
