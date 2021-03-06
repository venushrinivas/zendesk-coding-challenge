package com.zendesk.zcc.controller;

import com.zendesk.zcc.model.Request;
import com.zendesk.zcc.model.ZccRequestMethods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GetTickets {
  @RequestMapping(value = "api/v1/getTicketsInfo", method = RequestMethod.GET)
  @ResponseBody
  public String getTicketsInfo(Map<String,Object> model, HttpServletRequest request) {
    Map<String, String> params = new HashMap<>();
    params.put("LIMIT", request.getParameter("LIMIT") != null ? request.getParameter("LIMIT") : "25");
    params.put("OFF_SET", request.getParameter("OFF_SET") != null ? request.getParameter("OFF_SET") : "1");
    try {
      String requestUrl = request.getParameter("URL") != null ? request.getParameter("URL") : Request.GET_TICKETS.getBuilder(params).build();
      JSONObject jsonObject = new JSONObject(ZccRequestMethods.GET.getMethodHandler().sendRequest(requestUrl));
      if(jsonObject.has("tickets")) {
        JSONArray tickets = jsonObject.getJSONArray("tickets");
        JSONArray updatedTickets = new JSONArray();
        for (int i = 0; i < tickets.length(); i++) {
          JSONObject ticket = tickets.getJSONObject(i);
          JSONObject updatedTicket = new JSONObject();
          updatedTicket.put("url", ticket.get("url"));
          updatedTicket.put("subject", ticket.get("subject"));
          updatedTicket.put("id", ticket.get("id"));
          updatedTickets.put(updatedTicket);
        }
        jsonObject.put("tickets", updatedTickets);
      }
      return jsonObject.toString();
    } catch (IllegalArgumentException | IllegalStateException | IOException | JSONException e) {
      e.printStackTrace();
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("error", e.getMessage());
      return jsonObject.toString();
    }
  }
}
