package com.nojsoft.model;

import javax.persistence.Entity;

/**
 * Created by jorge on 23/01/17.
 */

@Entity
public class GroupSearch implements BaseModel {
    private String filter;
    private String value;

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }


}
