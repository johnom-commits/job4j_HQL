package ru.job4j.hql;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "candidates")
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int experience;

    private double salary;

    @OneToOne(fetch = FetchType.LAZY)
    private JobDatabase database;

    public static Candidate of(String name, int experience, double salary) {
        Candidate candidate = new Candidate();
        candidate.name = name;
        candidate.experience = experience;
        candidate.salary = salary;
        return candidate;
    }
}
