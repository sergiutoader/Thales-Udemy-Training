package com.luv2code.springmvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/home")
public class HomeController {

    @RequestMapping("/processForm")
    public String processForm() {
        return "processForm.html";
    }

    @RequestMapping("/form")
    public String form() {
        return "form.html";
    }

    @RequestMapping("/processFormv2")
    public String processFormUppercase(HttpServletRequest request, Model model) {
        String name = request.getParameter("studentName");

        name = name.toUpperCase();
        String result = "Yo, " + name;

        System.out.println(result);

        model.addAttribute("message", result);

        return "processForm.html";
    }

    @RequestMapping("/processFormv3")
    public String processFormVersionThree(@RequestParam("studentName") String name, Model model) {

        name = name.toUpperCase();
        String result = "Yo, " + name;

        System.out.println(result);

        model.addAttribute("message", result);

        return "processForm.html";
    }
}
