package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ibf2022.batch2.ssf.frontcontroller.model.User;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.validation.Valid;


@Controller
public class FrontController {

    @Autowired
    private AuthenticationService authSvc;

	// TODO: Task 2, Task 3, Task 4, Task 6
    
    //TASK1
    @GetMapping(path={"/","index.html"})
    public String view0(Model model, User user){
        model.addAttribute("user", new User());
        return "view0";
    }


    //TASK2
    @PostMapping(path={"/login"})
    public String view1(Model model, @Valid User user, BindingResult binding) throws Exception{
        if(binding.hasErrors()){
            model.addAttribute("user", user);
        return "view0";
        }
        // System.out.println(user);
        // System.out.println(User.createJsonfromObj(user).toString());
        //TODO- handle exception
        authSvc.authenticate(user.getUsername(), user.getPassword());

        return "view1";
    }
}
