package com.example.nationspring.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="nations")
@Data
@NoArgsConstructor
public class Nation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String capitalCity;
    private String language;
    private int population;

    public Nation(String capitalCity, String language, int population) {
        this.capitalCity = capitalCity;
        this.language = language;
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
            return this.capitalCity.equals(((Nation)o).capitalCity);
        }
}
