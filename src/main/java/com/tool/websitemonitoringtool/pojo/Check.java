package com.tool.websitemonitoringtool.pojo;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table
public class Check {

  
    private Integer checkId;

    private String uri;

    private Integer interval;

    public Integer getCheckId() {
        return checkId;
    }

    public void setCheckId(Integer checkId) {
        this.checkId = checkId;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }

    @Override
    public String toString() {
        return "Check{" +
                "checkId=" + checkId +
                ", uri='" + uri + '\'' +
                ", interval=" + interval +
                '}';
    }
}
