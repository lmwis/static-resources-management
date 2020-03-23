package com.fehead.open.srm.properties;

/**
 * @Description:
 * @Author: lmwis
 * @Date 2020-02-04 15:01
 * @Version 1.0
 */
public enum OSType {
    WINDOWS("WINDOWS"),
    MAC("MAC"),
    Linux("LINUX");

    String typeStr;

    OSType(String typeStr) {
        this.typeStr = typeStr;
    }

    public String getTypeStr() {
        return typeStr;
    }
}