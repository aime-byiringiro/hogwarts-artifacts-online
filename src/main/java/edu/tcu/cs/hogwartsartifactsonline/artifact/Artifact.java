package edu.tcu.cs.hogwartsartifactsonline.artifact;

import edu.tcu.cs.hogwartsartifactsonline.wizard.Wizard;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.List;

@Entity

public class Artifact implements Serializable {

    @Id

    private  String id;

    private String name;
    private String description;

    private String imageUrl;

    @ManyToOne // One own can own zero to many artifacts// Many artifacts to
    private Wizard owner;

    public void setOwner(Wizard owner) {
        this.owner = owner;
    }

    public Wizard getOwner() {
        return owner;
    }

    public Artifact() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
