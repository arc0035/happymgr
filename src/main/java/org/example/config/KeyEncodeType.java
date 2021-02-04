package org.example.config;

/**
 * 私钥按什么类型来保管
 * @author aaronchu
 * @Description
 * @data 2021/02/03
 */
public enum KeyEncodeType {

    /**
     * 明文
     */
    PLAIN,


    /**
     * PEM
     */
    PEM,

    /**
     * ETH Keystore类型
     */
    KEYSTORE,

    /**
     * PKCS12
     */
    PKCS12,

}
