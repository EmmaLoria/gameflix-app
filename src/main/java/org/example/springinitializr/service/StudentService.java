package org.example.springinitializr.service;

import org.example.springinitializr.model.Student;
import org.springframework.data.domain.Page;
import java.util.List;

public interface StudentService {
    List<Student> getAllStudents();
    void saveStudent(Student student);
    Student getStudentById(long sid);
    void deleteStudentById(long sid);
    Page<Student> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
