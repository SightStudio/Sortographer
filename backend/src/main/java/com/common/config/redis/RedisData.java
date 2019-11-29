package com.common.config.redis;

import java.io.Serializable;

public class RedisData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sourceId;
    private String itemId;

    public String getSourceId() {
        return sourceId;
    }
    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public RedisData() {}

    public RedisData(String sourceId, String itemId) {
        super();
        this.sourceId = sourceId;
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "DataType [sourceId=" + sourceId + ", itemId=" + itemId + "]";
    }
}
