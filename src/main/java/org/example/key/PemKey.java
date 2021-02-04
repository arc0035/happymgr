package org.example.key;

import org.example.config.EccTypeEnums;

import java.io.*;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/03
 */
public class PemKey implements EncodableKey {

    private byte[] plainKey;
    private EccTypeEnums eccType;

    public PemKey(byte[] plainKey, EccTypeEnums eccType){
        this.plainKey = plainKey;
        this.eccType = eccType;
    }

    @Override
    public void encode(OutputStream out) throws IOException {
        /*
        try{
            String pemStr = PemEncrypt.encryptPrivateKey(this.plainKey, eccType);
            out.write(pemStr.getBytes());
        }
        catch (Exception ex){
            throw new IOException(ex);
        }

         */
    }

    @Override
    public void decode(InputStream in) {
    }

    @Override
    public byte[] getPlainBytes() {
        return plainKey;
    }

    @Override
    public String toString(InputStream in) {
        //UTF-8 Decoding
        return null;
    }
}
