package com.zendesk.zcc.model;

import com.zendesk.zcc.Constants;

import java.util.Map;

public class GetTicketsRequestBuilder implements RequestBuilder {
  private final Map<String, String> params;

  public GetTicketsRequestBuilder(Map<String, String> params) {
    this.params = params;
  }

  @Override
  public String build() {
    return new StringBuilder().append("https://").append(System.getProperty(Constants.ZCC_SUB_DOMAIN)).append(".").append(Constants.DOMAIN).append(Request.GET_TICKETS.getApi()).append("?per_page=").append(params.get("LIMIT")).append("&page=").append(params.get("OFF_SET")).toString();
  }
}
