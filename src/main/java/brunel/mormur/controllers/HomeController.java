package brunel.mormur.controllers;

import brunel.mormur.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/sessionview")
    public String sessionview() {
        return "index";
    }

    @RequestMapping(value = "/userview")
    public String userview() {
        return "index";
    }
}