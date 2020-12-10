package com.lingo.project.word.core.domain;

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
public class Word {
    @Id
    @SequenceGenerator(name = "word_id_generator", sequenceName = "word_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_id_generator")
    private Long id;

    @Column(unique = true)
    public String value;

    @Column(columnDefinition = "boolean default false")
    public boolean is_deleted;
}
