package de.thro.inf.pvs;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Project {
    @Id
    @GeneratedValue
    private  Long id;
    private String name;

    @Transient
    @JsonIgnore
    private String internalInformation;

    @Version
    private Integer version;

    public Project() {
        this.name = "";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInternalInformation() {
        return internalInformation;
    }

    public void setInternalInformation(String internalInformation) {
        this.internalInformation = internalInformation;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", internalInformation='" + internalInformation +
                '}';
    }
}
