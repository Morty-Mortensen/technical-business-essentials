package com.essentials.business.technical.dao.http.sender;

import com.essentials.business.technical.dao.http.ClientCommunicator;

public class JNoodleSender {
    private static final String SERVER_URL = "https://api.jdoodle.com/v1/execute";
    private final ClientCommunicator clientCommunicator = new ClientCommunicator(SERVER_URL);
}
