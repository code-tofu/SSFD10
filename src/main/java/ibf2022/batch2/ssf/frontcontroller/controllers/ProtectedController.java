package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import ibf2022.batch2.ssf.frontcontroller.model.SessAuth;
import jakarta.servlet.http.HttpSession;

@Controller
public class ProtectedController {

	// TODO Task 5
    @GetMapping(path={"/protected/{path}"})
    public String protectView(@PathVariable String path, HttpSession session){
        SessAuth sessAuth = (SessAuth)session.getAttribute("sessAuth");
        if(null == sessAuth){
            return "redirect:/";
        } else if (sessAuth.isAuthFlag()){
            return "view1"; //USED AS EXAMPLE
            // return "redirect:/protected/{path}";
        }
        return "redirect:/";
    }
}
