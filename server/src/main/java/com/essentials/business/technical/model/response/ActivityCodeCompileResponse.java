package com.essentials.business.technical.model.response;

public class ActivityCodeCompileResponse {
    private String output;
    private int statusCode;
    private String memory;
    private String cpuTime;

    public ActivityCodeCompileResponse() {
    }

    public ActivityCodeCompileResponse(String output, int statusCode, String memory, String cpuTime) {
        this.output = output;
        this.statusCode = statusCode;
        this.memory = memory;
        this.cpuTime = cpuTime;
    }


    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMemory() {
        return memory;
    }

    public void setMemory(String memory) {
        this.memory = memory;
    }

    public String getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(String cpuTime) {
        this.cpuTime = cpuTime;
    }
}
