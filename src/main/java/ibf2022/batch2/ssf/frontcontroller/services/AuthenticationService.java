package ibf2022.batch2.ssf.frontcontroller.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;


import ibf2022.batch2.ssf.frontcontroller.model.User;
import jakarta.json.JsonObject;

@Service
public class AuthenticationService {

    @Value("${chuklee.auth.API.url}")
    private String chukAuthURL;
	//TASK2
	public boolean authenticate(String username, String password) throws Exception {
    // DO NOT CHANGE THE METHOD'S SIGNATURE
        RestTemplate restTemplate = new RestTemplate();
        JsonObject authJson = User.createJsonfromStr(username, password);
        RequestEntity<String> req = RequestEntity.post(chukAuthURL)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(authJson.toString(),String.class);
        System.out.println(chukAuthURL);
        System.out.println(authJson.toString());
        try{
            ResponseEntity<String> resp = restTemplate.exchange(req, String.class);
        } catch (HttpClientErrorException clientErr){
            
        }
        
        System.out.println(resp.getStatusCode());
        System.out.println(resp.getBody());
	}

	// TODO: Task 3
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to disable a user account for 30 mins
	public void disableUser(String username) {
	}

	// TODO: Task 5
	// DO NOT CHANGE THE METHOD'S SIGNATURE
	// Write an implementation to check if a given user's login has been disabled
	public boolean isLocked(String username) {
		return false;
	}
}
