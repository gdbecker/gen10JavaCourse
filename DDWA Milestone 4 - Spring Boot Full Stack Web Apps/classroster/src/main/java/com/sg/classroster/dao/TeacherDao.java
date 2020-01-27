package com.sg.classroster.dao;

import com.sg.classroster.entities.Teacher;
import java.util.List;

/**
 * @date Monday January 27, 2020
 * @author garrettbecker
 */

public interface TeacherDao {
    Teacher getTeacherById(int id);
    List<Teacher> getAllTeachers();
    Teacher addTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(int id);
}