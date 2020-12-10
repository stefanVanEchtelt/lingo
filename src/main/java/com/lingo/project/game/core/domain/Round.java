package com.lingo.project.game.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Round {

    @Id
    @SequenceGenerator(name = "round_id_generator", sequenceName = "round_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "round_id_generator")
    private Long id;

    private int tries;

//    @CreatedDate
//    @Column(nullable = false, updatable = false)
//    private Date createdAt;
}
