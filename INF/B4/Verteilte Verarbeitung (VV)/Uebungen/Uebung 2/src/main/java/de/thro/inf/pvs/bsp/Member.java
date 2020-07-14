package de.thro.inf.pvs.bsp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import de.thro.inf.pvs.Project;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Member {
    @Id
    @GeneratedValue
    private Long id;

    private String firstName;
    private String lastName;

    @Convert(converter = DateConverter.class)
    private LocalDate birthday;

    @Embedded
    private Address address;

    @JsonIgnore
    @ManyToOne
    //@ManyToMany
    private Project project;

    public Member(String firstName, String lastName, LocalDate birthday, Address address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
    }

    public Member() {
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String fristName) {
        this.firstName = fristName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", fristName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday=" + birthday +
                ", address=" + address +
                '}';
    }
}
