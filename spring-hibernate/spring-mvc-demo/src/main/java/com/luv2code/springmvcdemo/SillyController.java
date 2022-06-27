package com.luv2code.springmvcdemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/silly")
public class SillyController {

    @RequestMapping("/form")
    public String displayTheForm() {
        return "silly.html";
    }

}
