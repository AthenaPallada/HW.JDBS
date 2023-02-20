import dao.EmployeeDAO;
import dao.EmployeeDaoImpl;
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
        Employee employee1 = new Employee(1,"Polumna", "Lovegood", "female", 27, 4);
        Employee employee2 = new Employee(2,"Мая", "Алиева", "female", 13, 3);

        employeeDAO.create(employee1);
        employeeDAO.create(employee2);

        System.out.println(employeeDAO.readById(1));
        List<Employee> list = employeeDAO.readAll();
        for (Employee book : list) {
            System.out.println(book);
        }

        employeeDAO.updateById(employee1);
        employeeDAO.deleteById(employee1);
        employeeDAO.deleteById(employee2);





//
//        try (final Connection connection = DriverManager.getConnection(url, user, password);
//            PreparedStatement statement = connection.prepareStatement("SELECT first_name, last_name, gender, age, city_name FROM employee INNER JOIN city ON employee.id = city.city_id  WHERE id = (?)")) {
//            statement.setInt(1, 3);
//            final ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                String firstName = "First_name: " + resultSet.getString("first_name");
//                String lastName = "Last_name: " + resultSet.getString("last_name");
//                String gender = "Gender: " + resultSet.getString("gender");
//                String city = "City: " + resultSet.getString("city_name");
//                int age = resultSet.getInt(4);
//
//                System.out.println(firstName);
//                System.out.println(lastName);
//                System.out.println(gender);
//                System.out.println(city);
//                System.out.println("Age: " + age);
//            }
//        }
    }
}