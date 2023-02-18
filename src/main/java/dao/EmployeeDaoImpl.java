package dao;

import jdbc.ConnectionManager;
import model.City;
import model.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO {

    @Override
    public void create(Employee employee) throws SQLException {
        String INSERT = "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES ((?), (?), (?), (?), (?))";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT)) {
            statement.setString(1, employee.getFirstName());
            statement.setString(2, employee.getLastName());
            statement.setString(3, employee.getGender());
            statement.setInt(4, employee.getAge());
            statement.setInt(5, employee.getCity().getCityId());
            //statement.execute();
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Employee readById(int id) {
        Employee employee = new Employee();
        String GET = "SELECT * FROM employee INNER JOIN city ON employee.city_id = city.city_id WHERE id =(?)";
        try (Connection connection = ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(GET)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                employee.setFirstName(resultSet.getString("first_name"));
                employee.setLastName(resultSet.getString("last_name"));
                employee.setGender(resultSet.getString("gender"));
                employee.setAge(resultSet.getInt("age"));
                employee.setCity(new City(resultSet.getInt("city_id"),
                                            resultSet.getString("city_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> readAll() {
        List<Employee> employeeList = new ArrayList<>();
        String GET_ALL = "SELECT * FROM employee INNER JOIN city" + " ON employee.city_id=city.city_id";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(GET_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var id = Integer.parseInt(resultSet.getString("id"));
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                City city = new City(resultSet.getInt("city_id"),
                        resultSet.getString("city_name"));
                employeeList.add(new Employee(id,firstName, lastName, gender, age, city));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    @Override
    public void updateById(int id, String firstName, String lastName, String gender, int age) {
        String UPDATE = "UPDATE employee SET first_name = (?), last_name = (?), gender = (?), age = (?) WHERE id=(?)";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(UPDATE)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, gender);
            statement.setInt(4, age);
            statement.setInt(5, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteById(int id) {
        String DELETE = "DELETE FROM employee WHERE id=(?)";
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement statement = connection.prepareStatement(DELETE)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}