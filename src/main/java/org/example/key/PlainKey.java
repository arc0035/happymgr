package org.example.key;


import org.example.config.EccTypeEnums;
import org.fisco.bcos.sdk.utils.Numeric;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/03
 */
public class PlainKey implements EncodableKey {

    private byte[] bytes;
    private EccTypeEnums eccType;

    public PlainKey(byte[] bytes,EccTypeEnums eccType){
        this.bytes = bytes;
        this.eccType = eccType;
    }

    @Override
    public void encode(OutputStream out) throws IOException {
        out.write(this.bytes);
    }

    @Override
    public void decode(InputStream in) throws IOException{
        in.read(this.bytes, 0, this.bytes.length);
    }

    @Override
    public byte[] getPlainBytes() {
        return this.bytes;
    }

    @Override
    public String toString(InputStream in) {
        return Numeric.toHexString(this.bytes);
    }
}
