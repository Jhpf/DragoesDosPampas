package com.dell.ivr.beans.forms;

import javax.persistence.Entity;

@Entity
public class FormListAppsBean {

    private String console;
    private String port;
    private String username;
    private String password;
    private String targets;
    private String filePath;
    
        public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getConsole() {
	return console;
    }

    public void setConsole(String console) {
	this.console = console;
    }

    public String getPort() {
	return port;
    }

    public void setPort(String port) {
	this.port = port;
    }

}
