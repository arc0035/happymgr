package org.example.key;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/03
 */
public interface EncodableKey {

    /**
     * 将私钥明文编码为XXX格式（PEM，P12等）
     * @param out
     * @throws IOException
     */
    void encode(OutputStream out) throws IOException;

    /**
     * 从输入流读取XXX格式的密文，还原为明文
     * @param in
     * @throws IOException
     */
    void decode(InputStream in) throws IOException;

    /**
     * 获取私钥明文
     * @return
     */
    byte[] getPlainBytes();

    /**
     * 直接读取密文字符串
     * @param in
     * @return
     */
    String toString(InputStream in);
}
