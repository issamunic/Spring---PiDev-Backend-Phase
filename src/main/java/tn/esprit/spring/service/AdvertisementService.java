package tn.esprit.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.entities.Advertisement;
import tn.esprit.spring.repository.AdvertisementRepository;
import tn.esprit.spring.serviceInterface.IAdvertisementService;


@Service
@AllArgsConstructor
@Slf4j
public class AdvertisementService implements IAdvertisementService{
	@Autowired
	AdvertisementRepository adrepo;
	public void call_me(String message) throws Exception {
	     String url = "https://graph.facebook.com/v13.0/103810395594187/feed?message=&access_token=EAAT6hwoWu84BABeKFpRGW1fchVhTpFhf7TmFmaXE0pmzZAO9bf795G8RZBtZAxGhc3ksjtqFFyehcMJdnXKnaQZCtfGRvZAz9mXA4fMIpGZCCAjAu2jBSad1uh1bCPBj7BhZB2ZCs3OWoxuQC1eGdPklcTQZAPEtPsyFonG1tHPoeRR1YnVgxai72pvZBGZAMgCQjIZBJqZAa3MDYxVoX5QbnV82i";
	     insert(url,message);
	     URL obj = new URL(url);
	     HttpURLConnection con = (HttpURLConnection) obj.openConnection();
	     // optional default is GET
	     con.setRequestMethod("POST");
	     //add request header
	     con.setRequestProperty("User-Agent", "Mozilla/5.0");
	     int responseCode = con.getResponseCode();
	     System.out.println("\nSending 'POST' request to URL : " + url);
	     System.out.println("Response Code : " + responseCode);
	     BufferedReader in = new BufferedReader(
	             new InputStreamReader(con.getInputStream()));
	     String inputLine;
	     StringBuffer response = new StringBuffer();
	     while ((inputLine = in.readLine()) != null) {
	     	response.append(inputLine);
	     }
	     in.close();
	     //print in String
	     System.out.println(response.toString());
	
	}
	public static String insert(String url, String message) {
	    String bagBegin = url.substring(0,62);
	    String bagEnd =url.substring(62);
	    return bagBegin + message + bagEnd;
	}
	@Override
	public Advertisement Add(Advertisement ad) throws Exception {
		String message = ad.getText();
		adrepo.save(ad);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost("https://graph.facebook.com/v13.0/103810395594187/feed");

		// Request parameters and other properties.
		List<NameValuePair> params = new ArrayList<NameValuePair>(2);
		params.add(new BasicNameValuePair("message", message));
		params.add(new BasicNameValuePair("access_token", "EAAT6hwoWu84BABDU6hBYagWiVEMaJ9o2YmnqXMZBq3gRMyFuqZAfvysD"
				+ "kXHZBNanH2o8wFli5QTckIrszpflxiVJz6xqXm0HGYdzcyNGoNqlTrlNSC4Vj3fWWdZBk1D3dAlYcdHZBJq3Krc8Ik4OTeRjJJO"
				+ "JU8TZCFJsZA28XMfZCZBZA5KkNmV6fm"));
		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

		//Execute and get the response.
		CloseableHttpResponse response = httpclient.execute(httppost);
		HttpEntity entity = response.getEntity();

		if (entity != null) {
		    try (InputStream instream = entity.getContent()) {
		        System.out.println(instream);
		    }
		}
		return ad;
		
	}

}
