package org.example.springinitializr.service;

import org.example.springinitializr.model.Student;
import org.example.springinitializr.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentServiceImplTest {

    @Autowired
    private StudentRepository repository;

    @Test
    void getAllStudents() {
        List<Student> items = repository.findAll();
        assertEquals(5, items.size());
    }

    @Test
    public void testFindOne() {
        Student student = repository.findById(1L).get();
        assertEquals("David Peslak", student.getStudName());
    }
}
