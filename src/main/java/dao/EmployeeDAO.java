package dao;

import model.Employee;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    void create(Employee employee);

    Employee readById(int id);

    List<Employee> readAll();

    void updateById(Employee employee);

    void deleteById(Employee employee);
}
