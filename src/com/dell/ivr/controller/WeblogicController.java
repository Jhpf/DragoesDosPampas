package com.dell.ivr.controller;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dell.ivr.beans.Linux.LinuxServerBean;
import com.dell.ivr.beans.forms.FormListAppsBean;
import com.dell.ivr.beans.response.ResponseListApps;
import com.dell.ivr.beans.weblogic.WeblogicApplicationBean;
import com.dell.ivr.engine.LinuxSSHEngine;
import com.dell.ivr.engine.WeblogicEngine;

@Configuration
@PropertySource("/WEB-INF/wl.properties")
@Controller
public class WeblogicController {

    @Autowired
    private Environment env;

    @RequestMapping("/weblogicListAppsStart")
    public ModelAndView startPage() {

	ModelAndView mv = new ModelAndView("weblogic/listApps");
	mv.addObject("env", env);
	System.out.println(env.getProperty("USDEVConsole"));

	return mv;
    }

    @RequestMapping("loadWLListApps")
    public @ResponseBody
    ResponseListApps getWeblogicAppsList(@RequestBody FormListAppsBean form) {

	ResponseListApps result = new ResponseListApps();

	try {

	    LinuxServerBean linuxServerBean = new LinuxServerBean();
	    linuxServerBean.setHost(form.getConsole());
	    linuxServerBean.setPassword(form.getPassword());
	    linuxServerBean.setUsername(form.getUsername());
	    linuxServerBean.setHostPort(Integer.parseInt(form.getPort()));

	    LinuxSSHEngine linuxServer = new LinuxSSHEngine(linuxServerBean);

	    linuxServer.Connect();
	    JSONObject wlConfigJson = linuxServer.getServerConfiguration(env.getProperty("ConsoleConfigFile"));
	    linuxServer.Disconnect();

	    result.setApps(WeblogicEngine.extractApplicationList(wlConfigJson));
	    
	    result.setMessage(Integer.toString(result.getApps().size())+" Applications retrived");

	}
	catch (Exception e) {
	    result.setSuccess(false);
	    result.setMessage("Was not possible to retrive applications list. reason:" + e);
	}

	return result;
    }

}
