package de.thro.inf.pvs;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.thro.inf.pvs.bsp.Member;

import javax.persistence.*;
import java.util.List;

@Entity(name = "THPROJECT")
public class Project {
    @Id
    @GeneratedValue
    private  Long id;
    @Column(name = "projectname", length = 255, unique = true, nullable = false)
    private String name;

    @Transient
    @JsonIgnore
    private String internalInformation;

    @OneToMany(mappedBy = "project")
    //@ManyToMany
    private List<Member> members;

    @Version
    private Integer version;

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

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
                ", internalInformation='" + internalInformation + '\'' +
                ", members=" + members +
                ", version=" + version +
                '}';
    }
}
