package brunel.mormur.controllers;

import brunel.mormur.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/userview", method = RequestMethod.GET)
    public String userview() {
        return "index";
    }

    @RequestMapping(value = "/session/**")
    public String QARoom    () { return "index"; }
}