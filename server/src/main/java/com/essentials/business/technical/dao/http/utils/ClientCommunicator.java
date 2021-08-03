package com.essentials.business.technical.dao.http;

import com.essentials.business.technical.controller.exception.ErrorType;
import com.essentials.business.technical.dao.database.exception.DataAccessException;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class ClientCommunicator {
    private static final int TIMEOUT_MILLIS = 120000;
    private static final String POST = "POST";
    private static final String GET = "GET";

    private final String baseURL;

    public ClientCommunicator(String baseURL) {
        this.baseURL = baseURL;
    }

    private interface RequestStrategy {
        void setRequestMethod(HttpURLConnection connection) throws IOException;

        void sendRequest(HttpURLConnection connection) throws IOException;
    }

    <T> T doPost(String urlPath, final Object requestInfo, Map<String, String> headers, Class<T> returnType)
            throws IOException, DataAccessException {
        RequestStrategy requestStrategy = new RequestStrategy() {
            @Override
            public void setRequestMethod(HttpURLConnection connection) throws IOException {
                connection.setRequestMethod(POST);
            }

            @Override
            public void sendRequest(HttpURLConnection connection) throws IOException {
                connection.setDoOutput(true);

                String entityBody = JsonSerializer.serialize(requestInfo);
                try (DataOutputStream os = new DataOutputStream(connection.getOutputStream())) {
                    os.writeBytes(entityBody);
                    os.flush();
                }
            }
        };

        return doRequest(urlPath, headers, returnType, requestStrategy);
    }

    <T> T doGet(String urlPath, Map<String, String> headers, Class<T> returnType)
            throws IOException, DataAccessException {
        RequestStrategy requestStrategy = new RequestStrategy() {
            @Override
            public void setRequestMethod(HttpURLConnection connection) throws IOException {
                connection.setRequestMethod(GET);
            }

            @Override
            public void sendRequest(HttpURLConnection connection) {
                // Nothing to send. For a get, the request is sent when the connection is opened.
            }
        };

        return doRequest(urlPath, headers, returnType, requestStrategy);
    }

    private <T> T doRequest(String urlPath, Map<String, String> headers, Class<T> returnType, RequestStrategy requestStrategy)
            throws IOException, DataAccessException {

        HttpURLConnection connection = null;

        try {
            URL url = getUrl(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(TIMEOUT_MILLIS);
            requestStrategy.setRequestMethod(connection);

            if (headers != null) {
                for (String headerKey : headers.keySet()) {
                    connection.setRequestProperty(headerKey, headers.get(headerKey));
                }
            }

            requestStrategy.sendRequest(connection);

            switch (connection.getResponseCode()) {
                case HttpURLConnection.HTTP_OK:
                    String responseString = getResponse(connection.getInputStream());
                    return JsonSerializer.deserialize(responseString, returnType);
                case HttpURLConnection.HTTP_BAD_REQUEST:
                    ErrorResponse errorResponse = getErrorResponse(connection);
                    throw new DataAccessException(errorResponse.errorMessage, ErrorType.BAD_REQUEST);
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    errorResponse = getErrorResponse(connection);
                    throw new DataAccessException(errorResponse.errorMessage, ErrorType.INTERNAL_SERVER_ERROR);
                case HttpURLConnection.HTTP_FORBIDDEN:
                    errorResponse = getErrorResponse(connection);
                    throw new DataAccessException(errorResponse.errorMessage, ErrorType.FORBIDDEN);
                default:
                    throw new RuntimeException("An unknown error occurred. Response code = " + connection.getResponseCode());
            }
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private URL getUrl(String urlPath) throws MalformedURLException {
        String urlString = baseURL + (urlPath.startsWith("/") ? "" : "/") + urlPath;
        return new URL(urlString);
    }

    private ErrorResponse getErrorResponse(HttpURLConnection connection) throws IOException {
        String responseString = getResponse(connection.getErrorStream());
        if (responseString == null) {
            throw new RuntimeException("No response returned from server for response code " + connection.getResponseCode());
        } else {
            return JsonSerializer.deserialize(responseString, ErrorResponse.class);
        }
    }

    private String getResponse(InputStream inputStream) throws IOException {
        if (inputStream == null) {
            return null;
        } else {
            try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream))) {

                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }

                return response.toString();
            }
        }
    }

    /**
     * A class for de-serializing the json string the API Gateway returns with a 400 or 500 status
     * code.
     */
    @SuppressWarnings("unused")
    private static class ErrorResponse {
        private String errorMessage;
        private String errorType;
        private List<String> stackTrace;
    }
}
