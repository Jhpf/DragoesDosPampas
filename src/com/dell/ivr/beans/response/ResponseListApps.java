package com.dell.ivr.beans.response;

import java.util.ArrayList;

import javax.persistence.Entity;

import com.dell.ivr.beans.weblogic.WeblogicApplicationBean;

@Entity
public class ResponseListApps {
    
    private ArrayList<WeblogicApplicationBean> apps;
    private boolean success = true;
    private String message = "";

    public ArrayList<WeblogicApplicationBean> getApps() {
        return apps;
    }

    public void setApps(ArrayList<WeblogicApplicationBean> apps) {
        this.apps = apps;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
