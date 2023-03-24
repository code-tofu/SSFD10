package ibf2022.batch2.ssf.frontcontroller.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    @NotNull(message="Please key in your User Name")
    @Size(min=2, message="Username must be more than 2 characters in length")
    private String username;

    @NotNull(message="Please key in your Password")
    @Size(min=2, message="Username must be more than 2 characters in length")
    private String password;

    private Boolean authenticated = false;
    private int failCount = 0;


    //GETTERS AND SETTERS
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Boolean getAuthenticated() {
        return authenticated;
    }
    public void setAuthenticated(Boolean authenticated) {
        this.authenticated = authenticated;
    }
    public int getFailCount() {
        return failCount;
    }
    public void setFailCount(int failCount) {
        this.failCount = failCount;
    }

    //TOSTRING
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + ", authenticated=" + authenticated
                + ", failCount=" + failCount + "]";
    }

    
    public static JsonObject createJsonfromObj(User user){
        JsonObject userJson = Json.createObjectBuilder()
            .add("username",user.getUsername())
            .add("password",user.getPassword())
            .build();
        return userJson;
    }

    public static JsonObject createJsonfromStr(String username,String password){
        JsonObject userJson = Json.createObjectBuilder()
            .add("username",username)
            .add("password",password)
            .build();
        return userJson;
    }



}
