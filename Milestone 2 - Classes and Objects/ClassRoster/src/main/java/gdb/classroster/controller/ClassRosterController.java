package gdb.classroster.controller;

import gdb.classroster.dao.ClassRosterDao;
import gdb.classroster.dao.ClassRosterPersistenceException;
import gdb.classroster.dao.ClassRosterDaoFileImpl;
import gdb.classroster.dto.Student;
import gdb.classroster.service.ClassRosterDataValidationException;
import gdb.classroster.service.ClassRosterDuplicateIdException;
import gdb.classroster.service.ClassRosterServiceLayer;
import gdb.classroster.ui.ClassRosterView;
import gdb.classroster.ui.UserIO;
import gdb.classroster.ui.UserIOConsoleImpl;
import java.util.*;

/**
 * @date Thursday December 12, 2019
 * Monday December 16, 2019
 * @author garrettbecker
 */

public class ClassRosterController {
    ClassRosterView view;
    //ClassRosterDao dao; this is getting replaced with what's below:
    private ClassRosterServiceLayer service;
    
    //Constructor being modified to use the service layer
    public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        try {
            while (keepGoing) {
            
                menuSelection = getMenuSelection();
            
                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            
            }
        } catch (ClassRosterPersistenceException e){
            view.displayErrorMessage(e.getMessage());
        }
        
        exitMessage();
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    
    private void createStudent() throws ClassRosterPersistenceException {
        view.displayCreateStudentBanner();
        boolean hasErrors = false;
        do {
            Student currentStudent = view.getNewStudentInfo();
            try {
                service.createStudent(currentStudent);
                view.displayCreateSuccessBanner();
                hasErrors = false;
            } catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e) {
                hasErrors = true;
                view.displayErrorMessage(e.getMessage());
            }
        } while (hasErrors);
    }
    
    private void listStudents() throws ClassRosterPersistenceException {
        view.displayDisplayAllBanner();
        List<Student> studentList = service.getAllStudents();
        view.displayStudentList(studentList);
    }
    
    private void viewStudent() throws ClassRosterPersistenceException {
        view.displayDisplayStudentBanner();
        String studentId = view.getStudentIdChoice();
        Student student = service.getStudent(studentId);
        view.displayStudent(student);
    }
    
    private void removeStudent() throws ClassRosterPersistenceException {
        view.displayRemoveStudentBanner();
        String studentId = view.getStudentIdChoice();
        service.removeStudent(studentId);
        view.displayRemoveSuccessBanner();
    }
    
    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
    
    private void exitMessage() {
        view.displayExitBanner();
    }
}