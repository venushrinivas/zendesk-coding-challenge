package com.zendesk.zcc.model;

import com.zendesk.zcc.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.net.ssl.HttpsURLConnection;

public class GetRequestHandler implements RequestHandler {
  @Override
  public String sendRequest(String request) throws IOException {
    URL url = new URL(request);
    String encoding = Base64.getEncoder().encodeToString((System.getProperty(Constants.ZCC_USER) + ":" + System.getProperty(Constants.ZCC_PASS)).getBytes(StandardCharsets.UTF_8));
    HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url.openConnection();
    httpsURLConnection.setRequestMethod("GET");
    httpsURLConnection.setDoOutput(true);
    httpsURLConnection.setRequestProperty("Authorization", "Basic " + encoding);
    InputStream content = httpsURLConnection.getInputStream();
    BufferedReader in =
            new BufferedReader(new InputStreamReader(content));
    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = in.readLine()) != null) {
      sb.append(line);
    }
    return sb.toString();
  }
}
