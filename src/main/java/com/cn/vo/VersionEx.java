package com.cn.vo;

import com.cn.model.Commodity;
import com.cn.model.Version;

/**
 * Created by home on 2017/12/11.
 */
public class VersionEx extends Version {
   private String  noid;

    public String getNoid() {
        return noid;
    }

    public void setNoid(String noid) {
        this.noid = noid;
    }
}
