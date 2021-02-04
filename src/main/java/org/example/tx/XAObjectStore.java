package org.example.tx;

import javax.transaction.xa.XAResource;
import java.io.IOException;

/**
 * @author aaronchu
 * @Description
 * @data 2021/02/04
 */
public interface XAObjectStore {

    XAResource getXAResource();

}
