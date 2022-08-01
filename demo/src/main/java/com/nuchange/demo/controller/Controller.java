package com.nuchange.demo.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@RestController
public class Controller {
	
	private static final Logger log = LogManager.getLogger(Controller.class);
	int pageCount=1;
	Map<String, JsonObject> mapOfUrl=new HashMap<String, JsonObject>();
	Gson gson = new GsonBuilder().setPrettyPrinting()
			.serializeNulls().create();
	
	
	@GetMapping(value="storeurl")
	public void storeURL(@RequestParam("url") String URL) {
		try {
			log.fatal("Printing url: "+URL);
			if(!mapOfUrl.containsKey(URL)) {
				String uniqueKey = UUID.randomUUID().toString();
				JsonObject obj=new JsonObject();
				obj.addProperty("Page", pageCount++);
				obj.addProperty("Key", uniqueKey);
				obj.addProperty("Count", 0);
				mapOfUrl.put(URL, obj);
			}
		}catch (Exception e) {
			log.fatal("Error in storeURL api: ",e);
			throw e;
		}
	}
	
	@GetMapping(value="/get")
	public String getKey(@RequestParam("url") String URL) {
		try {
		String response="";
		//create unique short key
			if(mapOfUrl.containsKey(URL)) {
				int pageCnt=mapOfUrl.get(URL).get("Page").getAsInt();
				int count=mapOfUrl.get(URL).get("Count").getAsInt()+1;
				String key=mapOfUrl.get(URL).get("Key").getAsString();
				JsonObject tempObj=new JsonObject();
				tempObj.addProperty("Page", pageCnt);
				tempObj.addProperty("Key", key);
				tempObj.addProperty("Count", count);
				mapOfUrl.put(URL, tempObj);
				response=key;
				
			}else {
				response="Please add the URL before get";
			}
			return response;
		}catch (Exception e) {
			log.fatal("Error in geturl API: ",e);
			throw e;
		}
		
	}
	
	@GetMapping(value="/count")
	public int getCount(@RequestParam("url") String URL) {
		try {
			if(mapOfUrl.containsKey(URL)) {
				return mapOfUrl.get(URL).get("Count").getAsInt();
			}else {
				return 500;
			}
		}catch (Exception e) {
			log.fatal("Error in counturl API",e);
			throw e;
		}
	}
	
	@GetMapping(value="/list")
	public ResponseEntity<String> getList(@RequestParam("page") int page, @RequestParam("size") int size) {
		JsonObject responseObj=new JsonObject();
		try {
			for(Map.Entry<String, JsonObject> mapIterator: mapOfUrl.entrySet()) {
				int toValue=page+size;
				if(mapIterator.getValue().get("Page").getAsInt()>=page &&
						mapIterator.getValue().get("Page").getAsInt()<toValue) {
					responseObj.add(mapIterator.getKey(), mapIterator.getValue()); 
				}
			}
		}catch (Exception e) {
			log.fatal("Error");
			throw e;
		}
		return ResponseEntity.status(HttpStatus.OK).body(gson.toJson(responseObj));
	}
	
}
