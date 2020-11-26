package com.lingo.project.domain.game;

import javax.persistence.*;
import lombok.*;

@Entity
public class Game {

    @Id
    @SequenceGenerator(name = "game_id_generator", sequenceName = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    private Long id;

    private int score;
}
