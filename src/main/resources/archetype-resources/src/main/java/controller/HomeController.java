package ${groupId}.controller;

import ${groupId}.service.foo.FooDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private final FooDaoService fooDaoService;

    @Autowired
    public HomeController(FooDaoService fooDaoService) {
        this.fooDaoService = fooDaoService;
    }

    @RequestMapping("/")
    public String home() {
        return "hello";
    }
}
