package gdb.classroster.service;

import gdb.classroster.dao.ClassRosterAuditDao;
import gdb.classroster.dao.ClassRosterDao;
import gdb.classroster.dao.ClassRosterPersistenceException;
import gdb.classroster.dto.Student;
import java.util.List;

/**
 * @date Monday December 16, 2019
 * @author garrettbecker
 */

public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {

    ClassRosterDao dao;
    private ClassRosterAuditDao auditDao;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    public void validateStudentData(Student student) throws ClassRosterDataValidationException {
        
        if(student.getFirstName() == null 
                || student.getFirstName().trim().length() == 0
                || student.getLastName() == null
                || student.getLastName().trim().length() == 0
                || student.getCohort() == null
                || student.getCohort().trim().length() == 0) {
            throw new ClassRosterDataValidationException(
                    "ERROR: All Fields [First Name, Last Name, Cohort] are required.");
        }
    }
    
    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        //First check to see if there is already a student associated with the student id
        //If so, we're done. Throw a ClassRosterDuplicateIdException
        if (dao.getStudent(student.getStudentId()) != null) {
            throw new ClassRosterDuplicateIdException("ERROR: Could not create student. Student Id " + student.getStudentId() + " already exists.");
        }
        
        //Now validate all the fields of the given Student object
        //This method will throw an exception if any of the validation rules are violated
        validateStudentData(student);
        
        //We passed all of our business rules checks
        //Go ahead and persist the Student object
        dao.addStudent(student.getStudentId(), student);
        
        //The student was created successfully
        //Now write to the audit log
        auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED.");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
        return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        
        //Write to the audit log
        auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
        return removedStudent;
    }
    
}
