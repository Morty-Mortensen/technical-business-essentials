package com.essentials.business.technical.model.request;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ActivityCodeCompileRequest {
    private String clientId;
    private String clientSecret;
    private String script;
    private String language;
    private String versionIndex;

    public ActivityCodeCompileRequest() {
    }

    public ActivityCodeCompileRequest(String language, String versionIndex) {
        this.language = language;
        this.versionIndex = versionIndex;
    }

    public ActivityCodeCompileRequest(String script, String language, String versionIndex) {
        this.script = script;
        this.language = language;
        this.versionIndex = versionIndex;
    }

    public void setApiKeys() {
        JSONParser parser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) parser.parse(new FileReader("JDoodle.json"));
            this.clientId = (String) jsonObject.get("clientId");
            this.clientSecret = (String) jsonObject.get("clientSecret");
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getVersionIndex() {
        return versionIndex;
    }

    public void setVersionIndex(String versionIndex) {
        this.versionIndex = versionIndex;
    }
}

;
