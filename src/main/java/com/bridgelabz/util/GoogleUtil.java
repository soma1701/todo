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


public class GoogleUtil {
	private static final Logger LOG = (Logger) LogManager.getLogger(GoogleUtil.class);
	private static final String clientId="342823053592-pra6g4rhe59ka7rbpvq7lif712qsssqr.apps.googleusercontent.com";
	private static final String secreateKey="yCetJ8Tr2uuSFrm4GIcEwIfM";
	private static final String redirectUrl="http://localhost:8080/todo/successGoogleLogin";

	public static String generateGoogleUrl() {
		String googleLoginUrl="";
		try {
			googleLoginUrl="https://accounts.google.com/o/oauth2/auth?client_id=" + clientId + "&redirect_uri=" + 
					URLEncoder.encode(redirectUrl, "UTF-8") + "&response_type=code" + "&scope=profile email"
					+ "&approval_prompt=force" + "&access_type=offline";
		} catch (Exception e) {
			LOG.info("exception occured if google login url is not valid");
		}
		return googleLoginUrl;
	}

	
	public static String getAccessToken(String code) {
		 String urlParameters = "code=" + code + 
			       "&client_id=" + clientId +
			       "&client_secret=" + secreateKey + 
			       "&redirect_uri=" + URLEncoder.encode(redirectUrl) +
			       "&grant_type=authorization_code";
		 
		 try {
			URL url = new URL("https://accounts.google.com/o/oauth2/token");
			URLConnection connection = url.openConnection();
			connection.setDoOutput(true);
			OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream());
			writer.write(urlParameters);
			writer.flush();
			String googleResponse = "";
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String line;
			
			while((line = bufferedReader.readLine()) != null){
				googleResponse = googleResponse + line;
			}
			
			ObjectMapper objectMapper = new ObjectMapper();
			
			String googleAccessToken;
			try {
				googleAccessToken = objectMapper.readTree(googleResponse).get("access_token").asText();
				LOG.info("google aceess token by code:-"+googleAccessToken);
			} catch (IOException e) {
				LOG.info("exception occured if access token is null:-");
				e.printStackTrace();
				return null;
			}
			return googleAccessToken;
		} catch (IOException e) {
			e.printStackTrace();
		}
		 return null;
	}


	public static String getProfileData(String accessToken) {
		try {
			URL urlforGoogleProfile = new URL("https://www.googleapis.com/oauth2/v1/userinfo?access_token="+accessToken);
			LOG.info(urlforGoogleProfile);
			URLConnection connection = urlforGoogleProfile.openConnection();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String googleProfileInfo="";
			String line;
			while((line = bufferedReader.readLine())!= null) {
				googleProfileInfo = googleProfileInfo+line; 
			}
			return googleProfileInfo;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			LOG.info("connection refused if exception occured");
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
}
