import java.sql.SQLException;
import java.util.List;

public interface EmployeeDAO {

    void create(Employee employee) throws SQLException;

    Employee readById(int id);

    List<Employee> readAll();

    void updateAmountById(int id, int age);

    void deleteById(int id);
}
