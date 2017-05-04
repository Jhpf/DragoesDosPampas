package com.dell.ivr.engine;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.StringReader;
import java.util.Properties;

import org.json.JSONObject;
import org.json.XML;

import com.dell.ivr.beans.Linux.LinuxServerBean;
import com.jcraft.jsch.ChannelShell;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class LinuxSSHEngine {

    private Session session = null;
    private int connectionAttemps = 0;
    private LinuxServerBean serverBean;

    public static final String LAST_LINUX_CMD_STATS = "echo last command status: $?";
    public static final String LAST_lINUX_CMD_EXPECTED_STATS = "last command status: 0";

    public LinuxSSHEngine(LinuxServerBean linuxServerBean) {
	serverBean = linuxServerBean;
    }

    public void Connect() throws Exception {

	Properties sshConnectionProps = new Properties();
	JSch jsch = new JSch();

	try {
	    sshConnectionProps.setProperty("StrictHostKeyChecking", "no");
	    session = jsch.getSession(serverBean.getUsername(), serverBean.getHost(), serverBean.getHostPort());
	    session.setConfig(sshConnectionProps);
	    session.setPassword(serverBean.getPassword());
	    session.setTimeout(serverBean.getTimeOut());

	    System.out.println("Attempt to connect on server:" + serverBean.getHost());
	    session.connect(serverBean.getTimeOut());
	    System.out.println("Connected suscessfull on server:" + serverBean.getHost());

	}
	catch (Exception e) {
	    throw new Exception("Fail to connect. Reason:" + e);
	}

    }

    public void Disconnect() {
	session.disconnect();
	System.out.println("Server disconnected");
    }

    public JSONObject getServerConfiguration(String configFile) throws Exception {

	JSONObject json = new JSONObject();

	if (session == null || session.isConnected() == false) {
	    throw new Exception("Session disconnected");
	}

	try {
	    ChannelShell shell = null;
	    shell = (ChannelShell) session.openChannel("shell");
	    shell.connect();
	    DataInputStream in = new DataInputStream(shell.getInputStream());
	    DataOutputStream dataOut = new DataOutputStream(shell.getOutputStream());
	    byte[] tmp = new byte[2048];
	    String consoleTotalResult = "";
	    String consoleCmdResult = "";
	    consoleCmdResult = "";

	    // sending command
	    dataOut.writeBytes("cat " + configFile + "\n");
	    dataOut.flush();
	    Thread.sleep(2000); // give a second to process
	    dataOut.writeBytes(LAST_LINUX_CMD_STATS + "\n");
	    dataOut.flush();
	    Thread.sleep(250);

	    // read content back
	    while (in.available() > 0) {
		int j = in.read(tmp, 0, 2048);
		String aux = new String(tmp, 0, j).trim();
		consoleCmdResult = consoleCmdResult + aux;
		Thread.sleep(100); // time to read the stream
	    }
	    if (!hadCmdErrors(consoleCmdResult)) {
		String xmlContent = extractXMLfromCMDResult(consoleCmdResult);
		json = XML.toJSONObject(xmlContent);
	    }
	    else {
		throw new Exception("Not possible to read Config.xml content. an command error occured");
	    }
	}
	catch (Exception e) {
	    throw new Exception("Was not possible to get server configuration. Reason:" + e);
	}

	session.disconnect();
	System.out.println("Session disconnected");

	return json;

    }

    private String extractXMLfromCMDResult(String consoleCmdResult) throws Exception {
	String xml = "";

	if (consoleCmdResult != null && !consoleCmdResult.trim().equals("")) {
	    BufferedReader reader = new BufferedReader(new StringReader(consoleCmdResult));
	    String line = reader.readLine();
	    while (line != null) {
		if (line.startsWith("<") || line.endsWith(">")) {
		    xml = xml + line + "\n";
		}
		line = reader.readLine();
	    }

	}

	return xml;
    }

    private boolean hadCmdErrors(String consoleResult) {
	int i;
	String[] lines = consoleResult.split(System.getProperty("line.separator"));
	for (i = 0; i < lines.length; i++) {
	    if (lines[i].endsWith(LAST_LINUX_CMD_STATS)) {
		if (!lines[i + 1].trim().equals(LAST_lINUX_CMD_EXPECTED_STATS)) {
		    return true;
		}
	    }
	}

	return false;
    }

}
