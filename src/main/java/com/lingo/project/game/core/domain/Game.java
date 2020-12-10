package com.lingo.project.game.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Game {
    @Id
    @SequenceGenerator(name = "game_id_generator", sequenceName = "game_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "game_id_generator")
    private Long id;

    @Column(length = 32, columnDefinition = "varchar(32) default 'PROGRESS'")
    @Enumerated(EnumType.STRING)
    private Status status = Status.PROGRESS;
}
