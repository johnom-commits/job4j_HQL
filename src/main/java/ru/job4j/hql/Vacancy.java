package ru.job4j.hql;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter @NoArgsConstructor @EqualsAndHashCode
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String description;

    private double salary;

    public static Vacancy of(String description, double salary) {
        Vacancy v = new Vacancy();
        v.description = description;
        v.salary = salary;
        return v;
    }
}
