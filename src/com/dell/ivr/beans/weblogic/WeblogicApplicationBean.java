package com.dell.ivr.beans.weblogic;

import java.util.ArrayList;

import javax.persistence.Entity;

@Entity
public class WeblogicApplicationBean {

    private String name;
    private ArrayList<String> targets;
    private String path;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public ArrayList<String> getTargets() {
	return targets;
    }

    public void setTargets(ArrayList<String> targets) {
	this.targets = targets;
    }

    public String getPath() {
	return path;
    }

    public void setPath(String path) {
	this.path = path;
    }

}
