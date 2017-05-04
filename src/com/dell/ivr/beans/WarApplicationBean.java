package com.dell.ivr.beans;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class WarApplicationBean {

    private String jarFilePath;
    private String backupPath;
    private ArrayList<LinuxServerBean> targets;

    public String getJarFilePath() {
	return jarFilePath;
    }

    public ArrayList<LinuxServerBean> getTargets() {
	return targets;
    }

    public void setTargets(ArrayList<LinuxServerBean> targets) {
	this.targets = targets;
    }

    public void setJarFilePath(String jarFilePath) {
	this.jarFilePath = jarFilePath;
    }

    public String getBackupPath() {
	return backupPath;
    }

    public void setBackupPath(String backupPath) {
	this.backupPath = backupPath;
    }

}
