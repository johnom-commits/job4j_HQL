package ru.job4j.hql;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "jobs")
@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class JobDatabase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vacancy> vacancies = new ArrayList<>();

    public void addVacancy(Vacancy vacancy) {
        vacancies.add(vacancy);
    }

    public static JobDatabase of(String name) {
        JobDatabase j = new JobDatabase();
        j.name = name;
        return j;
    }
}
