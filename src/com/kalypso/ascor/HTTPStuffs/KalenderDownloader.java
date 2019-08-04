package com.kalypso.ascor.HTTPStuffs;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class KalenderDownloader {

    public KalenderDownloader(){

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Creates a new HTTP connection with the specified URL
    /// additionally, it sets the User agent to be Firefox so that our requests don't get bot-rejected and sets the
    /// request method to GET
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public HttpURLConnection createRequest(String UserURL){
        HttpURLConnection con = null;
        java.net.URL url = null;
        try {
            url = new URL(UserURL);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:25.0) Gecko/20100101 Firefox/25.0");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return con;
    }

    public HttpURLConnection addRequestParameters(HttpURLConnection con){
        Map<String, String> parameters = new HashMap<>();
        parameters.put("ev[addr]", "19938");
        parameters.put("str_20542","");

        con.setDoOutput(true);
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(con.getOutputStream());
            out.writeBytes(HTTPParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return con;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    /// Fetches the responseCode (implement custom handling of that if desired) and returns the fetched HTML in a String
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String parseOutput(HttpURLConnection con){
        StringBuffer content = new StringBuffer();
        try {
            int status = con.getResponseCode();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        con.disconnect();
        return content.toString();
    }

}
