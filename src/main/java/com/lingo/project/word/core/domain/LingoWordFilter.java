package com.lingo.project.word.core.domain;

import org.springframework.stereotype.Service;

@Service
public class LingoWordFilter implements WordFilter {

    @Override
    public boolean verify(String word) {
        return word.matches("^[a-z]{5,7}$");
    }
}
