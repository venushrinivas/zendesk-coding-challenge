package com.zendesk.zcc.model;

import java.util.Map;

public enum Request {
  GET_TICKETS("/api/v2/tickets.json");
  private final String api;

  Request(String api) {
    this.api = api;
  }

  public RequestBuilder getBuilder(Map<String, String> params) {
    switch (this) {
      case GET_TICKETS:
        return new GetTicketsRequestBuilder(params);
      default: throw new IllegalArgumentException("Invalid request");
    }
  }

  public String getApi() {
    return api;
  }
}
