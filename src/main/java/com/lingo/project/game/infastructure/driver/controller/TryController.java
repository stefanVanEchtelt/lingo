package com.lingo.project.game.infastructure.driver.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/try")
public class TryController {

    @PostMapping("/{word}")
    public void test(@PathVariable String word) {

    }
}
