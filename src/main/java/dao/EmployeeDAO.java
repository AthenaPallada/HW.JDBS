package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    void create(Employee employee) throws SQLException;

    Employee readById(int id);

    List<Employee> readAll();

    void updateById(int id,  String firstName, String lastName, String gender, int age);

    void deleteById(int id);
}
