import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
import dao.CityDAO;
import dao.CityDAOImpl;

import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "15MoOndjkr";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
//        Employee employee1 = new Employee(1,"Polumna", "Lovegood", "female", 27, 4);
//        Employee employee2 = new Employee(2,"Мая", "Алиева", "female", 13, 3);
//
//        employeeDAO.create(employee1);
//        employeeDAO.create(employee2);
//
//        System.out.println(employeeDAO.readById(1));
//        List<Employee> list = employeeDAO.readAll();
//        for (Employee book : list) {
//            System.out.println(book);
//        }
//
//        employeeDAO.updateById(employee1);
//        employeeDAO.deleteById(employee1);
//        employeeDAO.deleteById(employee2);

        CityDAO cityDAO = new CityDAOImpl();
        City city = new City("Санкт-Петербург");
        cityDAO.create(city);
        Employee employee = new Employee("Светлана", "Суркова", "female", 23, city);
        City city1 = cityDAO.getById(1);
        employee.setCity(city1);
        employeeDAO.create(employee);
        cityDAO.delete(city);
        List<City> list1 = cityDAO.getAllCities();
        for (City book : list1) {
           System.out.println(book);
        }


    }
}