/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gdb.classroster.service;

import gdb.classroster.dao.ClassRosterAuditDao;
import gdb.classroster.dao.ClassRosterAuditDaoStubImpl;
import gdb.classroster.dao.ClassRosterDao;
import gdb.classroster.dao.ClassRosterDaoStubImpl;
import gdb.classroster.dao.ClassRosterPersistenceException;
import gdb.classroster.dto.Student;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

/**
 *
 * @author garrettbecker
 */
public class ClassRosterServiceLayerTest {
    
    private ClassRosterServiceLayer service;
    
    public ClassRosterServiceLayerTest() {
        ClassRosterDao dao = new ClassRosterDaoStubImpl();
        ClassRosterAuditDao auditDao = new ClassRosterAuditDaoStubImpl();
        
        service = new ClassRosterServiceLayerImpl(dao, auditDao);
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of createStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testCreateStudent() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        
        service.createStudent(student);
        
        //Not testing the persistence but just the business rules 
        //Remember that the test cases for the DAO look at the data persistence
    }
    
    @Test
    public void testCreateStudentDuplicateId() throws Exception {
        Student student = new Student("0001");
        student.setFirstName("Sally");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        
        try {
            service.createStudent(student);
            fail("Expected ClassRosterDuplicateIdException was not thrown.");
        } catch (ClassRosterDuplicateIdException e) { 
            return;
        }
    }
    
    @Test
    public void testCreateStudentInvalidData() throws Exception {
        Student student = new Student("0002");
        student.setFirstName("");
        student.setLastName("Smith");
        student.setCohort("Java-Jan-2015");
        
        try {
            service.createStudent(student);
            fail("Expected ClassRosterDataValidationException was not thrown.");
        } catch(ClassRosterDataValidationException e) {
            return;
        }
    }

    /**
     * Test of getAllStudents method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetAllStudents() throws Exception {
        assertEquals(1, service.getAllStudents().size());
    }

    /**
     * Test of getStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testGetStudent() throws Exception {
        Student student = service.getStudent("0001");
        assertNotNull(student);
        student = service.getStudent("9999");
        assertNull(student);
    }

    /**
     * Test of removeStudent method, of class ClassRosterServiceLayer.
     */
    @Test
    public void testRemoveStudent() throws Exception {
        Student student = service.removeStudent("0001");
        assertNotNull(student);
        student = service.removeStudent("9999");
        assertNull(student);
    }
}
