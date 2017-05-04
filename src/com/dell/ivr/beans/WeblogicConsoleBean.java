package com.dell.ivr.beans;

import javax.persistence.Entity;

@Entity
public class WeblogicConsoleBean {

    private String host;
    private String hostPort;

    public String getHost() {
	return host;
    }

    public void setHost(String host) {
	this.host = host;
    }

    public String getHostPort() {
	return hostPort;
    }

    public void setHostPort(String hostPort) {
	this.hostPort = hostPort;
    }

}
