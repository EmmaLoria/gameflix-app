package org.example.springinitializr.model;

import jakarta.persistence.*;  // ✅ use jakarta, not javax
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Student")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long sid;

    @Column(name = "stud_name")
    private String studName;

    @Column(name = "stud_email")
    private String studEmail;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "sid"),
            inverseJoinColumns = @JoinColumn(name = "id")
    )
    private Set<Course> courses = new HashSet<>();

    // --- Getters and Setters ---

    public String getStudName() {
        return studName;
    }

    public void setStudName(String studName) {
        this.studName = studName;
    }

    public String getStudEmail() {
        return studEmail;
    }

    public void setStudEmail(String studEmail) {
        this.studEmail = studEmail;
    }

    public long getSid() {
        return sid;
    }

    public void setSid(long sid) {
        this.sid = sid;
    }

    public Set<Course> getCourses() {
        return courses;
    }

    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }
}
