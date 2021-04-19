package com.ss.dao;

import com.ss.entity.Airport;
import com.ss.entity.Employee;
import com.ss.entity.Flight;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAO extends BaseDAO<Employee> {
    public EmployeeDAO(Connection conn) {
        super(conn);
    }

    @Override
    public List<Employee> extractData(ResultSet rs) throws ClassNotFoundException, SQLException {
        List<Employee> employees = new ArrayList<>();
        while (rs.next()) {
            Employee e = new Employee();
            e.setId(rs.getInt("id"));
            e.setRoleId(rs.getInt("role_id"));
            e.setGivenName(rs.getString("given_name"));
            e.setFamilyName(rs.getString("family_name"));
            e.setUserName(rs.getString("username"));
            e.setEmail(rs.getString("email"));
            e.setPhone(rs.getString("phone"));
            employees.add(e);
        }
        return employees;
    }

    public Integer addEmployee(Employee employee) throws ClassNotFoundException, SQLException {
        return saveWithPK("INSERT INTO user (role_id, given_name, family_name, username," +
                        " email, password, phone) VALUES (?, ?, ?, ?, ?, ?, ?)",
                new Object[] { employee.getRoleId(), employee.getGivenName(),
                        employee.getFamilyName(), employee.getUserName(), employee.getEmail(),
                        employee.getPassword(), employee.getPhone()});
    }

    public Integer updateEmployee(Employee e) throws ClassNotFoundException, SQLException {
        return saveWithPK("UPDATE user SET given_name = ?, family_name = ?, username = ?," +
                        " email = ?, password = ?, phone = ? where id = ?",
                new Object[] { e.getGivenName(),
                        e.getFamilyName(), e.getUserName(), e.getEmail(),
                        e.getPassword(), e.getPhone(), e.getId() });
    }

    public void deleteEmployee(Employee e) throws ClassNotFoundException, SQLException {
        save("DELETE FROM user where id = ?",
                new Object[] { e.getId() });
    }

    public List<Employee> getAllEmployees() throws ClassNotFoundException, SQLException {
        return read("select * from user where role_id = 3", null);
    }
}
