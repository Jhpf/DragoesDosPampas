package com.dell.ivr.beans.Linux;

import javax.persistence.Entity;

@Entity
public class LinuxServerBean {

    private int maxConnectionRetries = 0;
    private int hostPort = 22;
    private String host = null;
    private String password = null;
    private String username = null;
    private int timeOut = 5000;

    public int getMaxConnectionRetries() {
	return maxConnectionRetries;
    }

    public void setMaxConnectionRetries(int maxConnectionRetries) {
	this.maxConnectionRetries = maxConnectionRetries;
    }

    public int getHostPort() {
	return hostPort;
    }

    public void setHostPort(int hostPort) {
	this.hostPort = hostPort;
    }

    public String getHost() {
	return host;
    }

    public void setHost(String host) {
	this.host = host;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public int getTimeOut() {
	return timeOut;
    }

    public void setTimeOut(int timeOut) {
	this.timeOut = timeOut;
    }

}
