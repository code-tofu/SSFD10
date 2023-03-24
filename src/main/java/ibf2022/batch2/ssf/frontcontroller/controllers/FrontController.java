package ibf2022.batch2.ssf.frontcontroller.controllers;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ibf2022.batch2.ssf.frontcontroller.model.SessAuth;
import ibf2022.batch2.ssf.frontcontroller.model.User;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import ibf2022.batch2.ssf.frontcontroller.services.CaptchaService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


@Controller
public class FrontController {

    @Autowired
    private AuthenticationService authSvc;

    @Autowired
    private CaptchaService captchaSvc;
    
    //TASK1
    @GetMapping(path={"/","index.html"})
    public String view0(Model model, User user,HttpSession session){
        // session.invalidate();
        model.addAttribute("user", new User());
        return "view0";
    }

    //TASK2/3/4
    @PostMapping(path={"/login"})
    public String view1(Model model, @Valid User user, BindingResult binding, HttpSession session){
        if(binding.hasErrors()){
            model.addAttribute("user", user);
            return "view0";
        }

        if(authSvc.isLocked(user.getUsername())){
            System.out.println(">>USER IS LOCKED");
            model.addAttribute("user", user);
            return "view2";
        }

        SessAuth sessAuth = (SessAuth)session.getAttribute("sessAuth");
        if(null == sessAuth){
            System.out.println(">>NEW sessAuth");
            sessAuth = new SessAuth();
            session.setAttribute("sessAuth",sessAuth);
        }
        System.out.println(sessAuth);

        if(sessAuth.isCaptchaFlag()){
            System.out.println(">>CAPTCHA ACTIVE");
            String[] captchaArr = (String [])session.getAttribute("captcha");
            if(null != captchaArr){
                System.out.println(Arrays.toString(captchaArr));
                System.out.println(user.getCaptchaAnswer());
                
                if(CaptchaService.evaluateCaptcha(captchaArr,new BigDecimal(user.getCaptchaAnswer()))){
                    System.out.println(">>CAPTCHA PASSED");
                    sessAuth.setCaptchaFlag(false);

                } else {
                    System.out.println(">>CAPTCHA FAILED");
                    binding.addError(
                        new FieldError("user", "captchaAnswer", "Incorrect Captcha")
                        );  
                    model.addAttribute("user", user);
                    captchaArr = CaptchaService.generateCaptcha(); //
                    session.setAttribute("captcha",captchaArr);
                    model.addAttribute("captcha", captchaArr);
                }
            }
        }
        if(!sessAuth.isCaptchaFlag()){
            int authenticateInt = authSvc.authenticate(user.getUsername(), user.getPassword());
            if(1 == authenticateInt) sessAuth.setAuthFlag(true);
            if(1 != authenticateInt){
                    String[] captchaArr = (String [])session.getAttribute("captcha");
                    System.out.println(">>AUTH FAILED");
                    sessAuth.setCaptchaFlag(true);
                    System.out.println(">>NEW CAPTCHA");
                    captchaArr = CaptchaService.generateCaptcha();
                    session.setAttribute("captcha",captchaArr);
                    model.addAttribute("captcha", captchaArr); 
                //ADD ERROR MESSAGES
                if(400 == authenticateInt){
                    binding.addError(
                        new FieldError("user", "captchaAnswer", "Invalid Payload")
                    );
                    model.addAttribute("user", user);
                }
                if(401 == authenticateInt){
                    binding.addError(
                        new FieldError("user", "captchaAnswer", "Incorrect username and/or password")
                    );
                    model.addAttribute("user", user);
                }
            }
        }


        if(sessAuth.isAuthFlag() && (!sessAuth.isCaptchaFlag())){
            System.out.println(">>AUTH PASSED");
            return "view1";
        } else {
            sessAuth.setFailCount(sessAuth.getFailCount()+1);
            session.setAttribute("sessAuth",sessAuth);
            System.out.println(">>FAILCOUNT:" + Integer.toString(sessAuth.getFailCount()));
            if(sessAuth.getFailCount()>2) authSvc.disableUser(user.getUsername());
        }
            return "view0";
    }

    //TASK 6
    @GetMapping(path={"/logout"})
    public String logout(HttpSession session){
        session.invalidate();
        System.out.println(">>LOGGING OUT");
        return "redirect:/";
    }
    
}
