package com.lingo.project.domain;

import javax.persistence.*;

@Entity
public class Game {

    @Id
    @SequenceGenerator(name = "game_id_generator", sequenceName = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    private Long id;

    private String name;
}
