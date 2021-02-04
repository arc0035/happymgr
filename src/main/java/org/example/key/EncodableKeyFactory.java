package org.example.key;

import org.example.config.EccTypeEnums;
import org.example.config.KeyEncodeType;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/03
 */
public class EncodableKeyFactory {

    public EncodableKey buildEncodableKey(String hexKey, KeyEncodeType encodeType, EccTypeEnums eccType){
        byte[] keyData = Numeric.hexStringToByteArray(hexKey);
        if(keyData.length != 32) throw new IllegalArgumentException("Invalid key bytes length:"+keyData.length);
        switch (encodeType){
            case PEM:
                return new PemKey(keyData, eccType);
            case PLAIN:
                return new PlainKey(keyData, eccType);
            default:throw new UnsupportedOperationException();
        }
    }

}
