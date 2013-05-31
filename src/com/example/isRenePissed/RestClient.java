package com.example.isRenePissed;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jmoran
 * Date: 5/31/13
 * Time: 4:39 PM
 * To change this template use File | Settings | File Templates.
 */
public class RestClient {

    private static String protocol = "http";
    private static String domain   = "isrenepissed.com";

    private HttpClient client;
    private List<NameValuePair> urlParams;

    public RestClient()
    {
        this.client = new DefaultHttpClient();
        this.urlParams = new LinkedList<NameValuePair>();
        this.addUrlParam("format", "json");

    }

    public RestClient addUrlParam(String name, String value)
    {
        this.urlParams.add(new BasicNameValuePair(name, value));
        return this;
    }

    public String getRequestString()
    {
        return String.format("%s://%s?%s", protocol, domain, URLEncodedUtils.format(this.urlParams, "utf-8"));
    }

    public String getIsPissed()
    {
        try {
            return this.doGet().getString("status");
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    private JSONObject doGet() throws TooPissedToRespondException
    {
        JSONObject jsonResponse = null;
        HttpResponse response   = null;

        HttpGet get = new HttpGet(this.getRequestString());

        try {
            response = this.client.execute(get);
        } catch (IOException e) {
            throw new TooPissedToRespondException("Sorry, he is too pissed to respond right now.");
        }

        if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
            throw new TooPissedToRespondException("He is having a really bad day.");
        }

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        try {
            jsonResponse = new JSONObject(responseHandler.handleResponse(response));
        } catch (Exception e) {
            throw new TooPissedToRespondException("He is so pissed he cannot talk right");
        }

        return jsonResponse;
    }
}
