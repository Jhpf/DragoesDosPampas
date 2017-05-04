package com.dell.ivr.engine;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dell.ivr.beans.weblogic.WeblogicApplicationBean;

public class WeblogicEngine {

    public static ArrayList<WeblogicApplicationBean> extractApplicationList(JSONObject wlConfigJson) throws Exception {
	int i;
	ArrayList<WeblogicApplicationBean> result = new ArrayList<WeblogicApplicationBean>();
	JSONObject jsonBean = null;
	WeblogicApplicationBean app = null;

	try {

	    JSONArray jsonTargets = wlConfigJson.getJSONObject("domain").getJSONArray("server");
	    Hashtable<String, String> targets = new Hashtable<String, String>();
	    for (i = 0; i < jsonTargets.length(); i++) {
		jsonBean = jsonTargets.getJSONObject(i);
		if (jsonBean.has("name") && jsonBean.has("machine")) {
		    targets.put(jsonBean.getString("name"), jsonBean.getString("machine"));
		}
	    }

	    JSONArray array = wlConfigJson.getJSONObject("domain").getJSONArray("app-deployment");
	    for (i = 0; i < array.length(); i++) {
		jsonBean = array.getJSONObject(i);
		app = new WeblogicApplicationBean();
		app.setName(jsonBean.getString("name"));
		app.setPath(jsonBean.getString("source-path"));
		app.setTargets(extractTargetNames(jsonBean.get("target").toString(), targets));
		result.add(app);
	    }
	}
	catch (Exception e) {
	    throw new Exception("not possible to extract app list from Json Config object. reason:" + e);
	}

	return result;
    }

    private static ArrayList<String> extractTargetNames(String targetsList, Hashtable<String, String> servers) {
	ArrayList<String> result = new ArrayList<String>();

	StringTokenizer toke = new StringTokenizer(targetsList,",");
	while (toke.hasMoreElements()) {
	    String serverName = toke.nextToken();
	    result.add(servers.get(serverName));
	}

	return result;
    }

}
