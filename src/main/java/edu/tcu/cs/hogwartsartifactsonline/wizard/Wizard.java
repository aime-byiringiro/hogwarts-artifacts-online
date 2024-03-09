package edu.tcu.cs.hogwartsartifactsonline.wizard;

import edu.tcu.cs.hogwartsartifactsonline.artifact.Artifact;
import jakarta.persistence.*;

import java.io.Serializable;

import java.util.List;


@Entity
public class Wizard implements Serializable {

    @Id

    private Integer id;

    private String name;

    @OneToMany(cascade ={CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "owner") // ONe wizard to many Artifacts
    private  List <Artifact> artifacts;

    public Wizard() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Artifact> getArtifacts() {
        return artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }
}
