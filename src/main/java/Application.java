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
        City city1 = new City(4, "London");
        Employee employee1 = new Employee("Polumna", "Lovegood", "female", 20, city1);

        City city2 = new City(5, "Новочик");
        Employee employee2 = new Employee("Мая", "Алиева", "female", 13, city2);

//        employeeDAO.create(employee1);
//        employeeDAO.create(employee2);

        List<Employee> list = new ArrayList<>(employeeDAO.readAll());
        for (Employee book : list) {
            System.out.println(book);
        }

        employeeDAO.updateById(34, "Sofia", "Sunrise", "female", 33);

        employeeDAO.deleteById(32);


        try (final Connection connection = DriverManager.getConnection(url, user, password);
            PreparedStatement statement = connection.prepareStatement("SELECT first_name, last_name, gender, age, city_name FROM employee INNER JOIN city ON employee.id = city.city_id WHERE id = (?)")) {
            statement.setInt(1, 3);
            final ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String firstName = "First_name: " + resultSet.getString("first_name");
                String lastName = "Last_name: " + resultSet.getString("last_name");
                String gender = "Gender: " + resultSet.getString("gender");
                String city = "model.City: " + resultSet.getString("city_name");
                int age = resultSet.getInt(4);

                System.out.println(firstName);
                System.out.println(lastName);
                System.out.println(gender);
                System.out.println(city);
                System.out.println("Age: " + age);
            }
        }
    }
}