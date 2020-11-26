package com.lingo.project.domain.word;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Word {

    @Id
    @SequenceGenerator(name = "word_id_generator", sequenceName = "word_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "word_id_generator")
    private Long id;

    private String value;

    private boolean isDeleted;

    public boolean isValid() {
        return this.value != null && this.isCorrectSize() && !this.isUpperCase() && !this.hasSpecialCars();
    }

    public boolean isCorrectSize() {
        return this.value != null && this.value.length() <= 7 && this.value.length() >= 5;
    }

    public boolean isUpperCase() {
        return this.value != null && this.value.matches("(.*[A-Z].*)");
    }

    public boolean hasSpecialCars() {
        return this.value != null && !(this.value.matches("[a-zA-Z]+"));
    }
}
