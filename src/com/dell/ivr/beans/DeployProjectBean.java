package com.dell.ivr.beans;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class DeployProjectBean {

    private String name;
    private String backUpPath;
    private String storePath;
    private String projectPath;
    private String deployDate;
    private WeblogicConsoleBean console;
    private ArrayList<WarApplicationBean> warApplications;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getBackUpPath() {
	return backUpPath;
    }

    public void setBackUpPath(String backUpPath) {
	this.backUpPath = backUpPath;
    }

    public String getStorePath() {
	return storePath;
    }

    public void setStorePath(String storePath) {
	this.storePath = storePath;
    }

    public String getProjectPath() {
	return projectPath;
    }

    public void setProjectPath(String projectPath) {
	this.projectPath = projectPath;
    }

    public String getDeployDate() {
	return deployDate;
    }

    public void setDeployDate(String deployDate) {
	this.deployDate = deployDate;
    }

    public WeblogicConsoleBean getConsole() {
	return console;
    }

    public void setConsole(WeblogicConsoleBean console) {
	this.console = console;
    }

    public ArrayList<WarApplicationBean> getWarApplications() {
	return warApplications;
    }

    public void setWarApplications(ArrayList<WarApplicationBean> warApplications) {
	this.warApplications = warApplications;
    }

}
