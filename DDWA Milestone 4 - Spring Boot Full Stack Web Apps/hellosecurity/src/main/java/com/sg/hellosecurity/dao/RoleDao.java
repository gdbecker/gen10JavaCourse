package com.sg.hellosecurity.dao;

import com.sg.hellosecurity.entities.Role;
import java.util.List;

/**
 * @date Friday January 31, 2020
 * @author garrettbecker
 */

public interface RoleDao {
    Role getRoleById(int id);
    Role getRoleByRole(String role);
    List<Role> getAllRoles();
    void deleteRole(int id);
    void updateRole(Role role);
    Role createRole(Role role);
}
