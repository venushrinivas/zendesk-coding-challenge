package com.zendesk.zcc.model;

import java.io.IOException;

public interface RequestHandler {
  String sendRequest(String request) throws IOException;
}
