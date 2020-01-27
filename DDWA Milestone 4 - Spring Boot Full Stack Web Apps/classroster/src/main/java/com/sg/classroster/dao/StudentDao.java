package com.sg.classroster.dao;

import com.sg.classroster.entities.Student;
import java.util.List;

/**
 * @date Monday January 27, 2020
 * @author garrettbecker
 */

public interface StudentDao {
    Student getStudentById(int id);
    List<Student> getAllStudents();
    Student addStudent(Student student);
    void updateStudent(Student student);
    void deleteStudentById(int id);
}
