package com.essentials.business.technical.dao.http.utils;

import com.essentials.business.technical.controller.exception.TBEServerException;
import com.essentials.business.technical.controller.exception.TBEBadRequestException;
import com.essentials.business.technical.controller.exception.TBEForbiddenException;
import com.essentials.business.technical.controller.exception.TBEInternalServerErrorException;
import org.springframework.http.HttpHeaders;

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

    public <T> T doPost(String urlPath, final Object requestInfo, Map<String, String> headers, Class<T> returnType)
            throws TBEServerException {
        RequestStrategy requestStrategy = new RequestStrategy() {
            @Override
            public void setRequestMethod(HttpURLConnection connection) throws IOException {
                connection.setRequestMethod(POST);
            }

            @Override
            public void sendRequest(HttpURLConnection connection) throws IOException {
                connection.setDoOutput(true);
                String entityBody = JsonSerializer.serialize(requestInfo);
                OutputStream outputStream = connection.getOutputStream();
                outputStream.write(entityBody.getBytes());
                outputStream.flush();
            }
        };

        return doRequest(urlPath, headers, returnType, requestStrategy);
    }

    public <T> T doGet(String urlPath, Map<String, String> headers, Class<T> returnType)
            throws IOException, TBEServerException {
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
            throws TBEServerException {

        HttpURLConnection connection = null;

        try {
            URL url = getUrl(urlPath);
            connection = (HttpURLConnection) url.openConnection();
            connection.setReadTimeout(TIMEOUT_MILLIS);
            requestStrategy.setRequestMethod(connection);

            connection.setRequestProperty(HttpHeaders.CONTENT_TYPE, "application/json");

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
                    String errorResponse = getErrorResponse(connection);
                    throw new TBEBadRequestException(errorResponse);
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    errorResponse = getErrorResponse(connection);
                    throw new TBEInternalServerErrorException(errorResponse);
                case HttpURLConnection.HTTP_FORBIDDEN:
                    errorResponse = getErrorResponse(connection);
                    throw new TBEForbiddenException(errorResponse);
                default:
                    throw new RuntimeException("An unknown error occurred. Response code = " + connection.getResponseCode());
            }
        } catch (IOException ex) {
            throw new TBEInternalServerErrorException("Unable to parse error stream.", ex);
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

    private String getErrorResponse(HttpURLConnection connection) throws TBEServerException {
        try {
            String responseString = getResponse(connection.getErrorStream());
            if (responseString == null) {
                throw new RuntimeException("No response returned from server for response code " + connection.getResponseCode());
            } else {
                return responseString;
            }
        } catch (IOException ex) {
            throw new TBEInternalServerErrorException("Unable to parse error stream.", ex);
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
