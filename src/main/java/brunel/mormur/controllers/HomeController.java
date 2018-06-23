package brunel.mormur.controllers;

import brunel.mormur.*;
import brunel.mormur.views.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }
}