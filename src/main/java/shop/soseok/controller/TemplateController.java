package shop.soseok.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class TemplateController {

    @GetMapping()
    public String index(Model model) {
        List<String> newlyArrived = new ArrayList<>(Arrays.asList(
                "bookmark/1.jpg", "bracelet/1.jpg", "keyring/1.jpg",
                "necklace/1.jpg", "bookmark/2.jpg", "bracelet/2.jpg",
                "keyring/2.jpg"
        ));
        List<String> bestSellers = new ArrayList<>(Arrays.asList(
                "bookmark/3.jpg", "bracelet/3.jpg", "keyring/3.jpg",
                "necklace/3.jpg", "bookmark/4.jpg", "bracelet/4.jpg"
        ));

        model.addAttribute("newlyArrived", newlyArrived);
        model.addAttribute("bestSellers", bestSellers);
        return "index";
    }
}
