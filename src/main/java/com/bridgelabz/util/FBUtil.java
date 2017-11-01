package com.bridgelabz.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

public  class FBUtil {
	private static final Logger LOG = (Logger) LogManager.getLogger(GoogleUtil.class);
	private static final String fclientId="129892814380911";
	private static final String fsecreateKey="27c80b7e7dfd9a35b13adfe094958aa9";
	private static final String fredirectUrl="http://localhost:8080/todo/successFbLogin";
	private static final String USER_ACCESS_URL = "https://graph.facebook.com/v2.9/me?access_token=";
	private static final String BINDING = "&fields=id,name,email,first_name,last_name,picture";
	
	public static String generateFbUrl() {
		String fbUrl="";
		try {
			 fbUrl = "https://www.facebook.com/v2.10/dialog/oauth?" + "client_id=" + fclientId + "&redirect_uri="
					+ URLEncoder.encode(fredirectUrl) + "&state=todoappstate" + "&scope=public_profile,email";
			
			
		} catch (Exception e) {
			LOG.catching(e);
		}
		return fbUrl;
		
	}
	public static String getFbAccessToken(String code) {
		String urlParametersForFb = "&redirect_uri=" + URLEncoder.encode(fredirectUrl)
		+ "&client_secret=" + fsecreateKey 
		+ "&code=" + code;
		try {
			URL url = new URL("https://graph.facebook.com/v2.10/oauth/access_token");

			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParametersForFb);
			writer.flush();
			
			String fbResponse = "";
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			
			while ((line = bufferedReader.readLine()) != null) {
				fbResponse = fbResponse + line;
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			String fbAccessToken = objectMapper.readTree(fbResponse).get("access_token").asText();
			LOG.info(fbAccessToken);
			
			return fbAccessToken;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static String getProfileInfoFromFb(String accessTokenForFb) {
		
		try {
			URL urlForFb = new URL(USER_ACCESS_URL+accessTokenForFb+BINDING);
			LOG.info(urlForFb);
			URLConnection connection = urlForFb.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String fbProfileInfo="";
			String line;
			while((line=bufferedReader.readLine())!=null) {
				fbProfileInfo = line +fbProfileInfo;
			}
			return fbProfileInfo;
		} catch (Exception e) {
			LOG.catching(e);
			LOG.info("exception occured while getting url for fb:-");
		}
		return null;
	}
	

}
