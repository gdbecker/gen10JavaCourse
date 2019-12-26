package gdb.classroster.service;

import gdb.classroster.dao.ClassRosterPersistenceException;
import gdb.classroster.dto.Student;
import java.util.List;

/**
 * @date Monday December 16, 2019
 * @author garrettbecker
 */

public interface ClassRosterServiceLayer {
    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;
    
    List<Student> getAllStudents() throws
            ClassRosterPersistenceException;
    
    Student getStudent(String studentId) throws
            ClassRosterPersistenceException;
    
    Student removeStudent(String studentId) throws
            ClassRosterPersistenceException;
}
