package com.neil.module_lib.net.core;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * map工具，嵌套接口中
 */
public abstract class BaseMap<T> implements Serializable {
    private Map<String, T> map = new LinkedHashMap<String, T>();

    protected BaseMap() {
        initMap(getMap());
    }

    protected abstract void initMap(Map<String, T> map); // initParams

    /**
     * TODO 返回map
     */
    public Map<String, T> getMap() { // map管理
        return map;
    }

}