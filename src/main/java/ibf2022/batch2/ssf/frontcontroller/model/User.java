package ibf2022.batch2.ssf.frontcontroller.model;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class User {

    @NotNull(message="Please key in your User Name")
    @Size(min=2, message="Username must be more than 2 characters in length")
    private String username;

    @NotNull(message="Please key in your Password")
    @Size(min=2, message="Password must be more than 2 characters in length")
    private String password;

    @NotBlank(message="Please key in the Captcha")
    private String captchaAnswer = "0.00";

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
    public String getCaptchaAnswer() {
        return captchaAnswer;
    }
    public void setCaptchaAnswer(String captchaAnswer) {
        this.captchaAnswer = captchaAnswer;
    }
    

    //TOSTRING
    @Override
    public String toString() {
        return "User [username=" + username + ", password=" + password + "]" ;
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
