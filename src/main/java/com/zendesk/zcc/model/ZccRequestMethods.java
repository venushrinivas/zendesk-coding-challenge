package com.zendesk.zcc.model;

public enum ZccRequestMethods {
  GET, POST;

  public RequestHandler getMethodHandler() {
    switch (this) {
      case GET:
        return new GetRequestHandler();
      default: throw new IllegalArgumentException("Operation not supported");
    }
  }
}
