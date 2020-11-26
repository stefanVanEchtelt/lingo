package com.lingo.project.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/game")
public class GameController {

    @GetMapping(path = "/find")
    public String find() {
        return "Find game that's in progress or old game that's done";
    }

    @GetMapping(path = "/start")
    public String start() {
        return "Start game";
    }
}
