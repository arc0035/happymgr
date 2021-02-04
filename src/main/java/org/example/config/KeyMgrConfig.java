package org.example.config;

import lombok.Getter;
import org.example.config.EccTypeEnums;
import org.example.config.KeyEncodeType;

import javax.transaction.xa.XAResource;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/04
 */
@Getter
public class KeyMgrConfig {

    /**
     * Key encryption type
     */
    private KeyEncodeType keyEncodeType;

    /**
     * 支持的曲线类型
     */
    private EccTypeEnums eccType;
}
