package fau.dataMart.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@jakarta.persistence.Entity(name = "Metadata")
@Table(name = "Metadata")
public class Metadata {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToMany(mappedBy = "metadata")
    private Set<Query> queries = new HashSet<>();


    public Metadata(String name) {
        this.name = name;
    }

    public Metadata() {

    }

    public int getId(){
        return id;
    }

    public String getName() {
        return name;
    }
}

