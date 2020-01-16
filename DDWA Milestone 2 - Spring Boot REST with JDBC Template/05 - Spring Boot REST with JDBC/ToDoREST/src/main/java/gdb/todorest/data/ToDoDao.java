package gdb.todorest.data;

import gdb.todorest.models.ToDo;
import java.util.List;

/**
 * @date Thursday January 16, 2020
 * @author garrettbecker
 */

public interface ToDoDao {
    ToDo add(ToDo todo);

    List<ToDo> getAll();

    ToDo findById(int id);

    // true if item exists and is updated
    boolean update(ToDo todo);

    // true if item exists and is deleted
    boolean deleteById(int id);
}
