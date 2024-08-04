import model.Employees;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseOperations {
    private static final String SELECT_ALL = "SELECT * FROM employees ORDER BY id";
    private static final String SELECT_PARAMETRIZED = "SELECT * FROM employees WHERE company = ? ORDER BY id";
    private static final String INSERT = "INSERT INTO employees (id, first_name, last_name, age, country, company, salary) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE = "UPDATE employees SET first_name = ? WHERE id = ?";
    private static final String DELETE = "DELETE FROM employees WHERE id = ?";

    private DatabaseOperations() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static List<Employees> getEmployees() {
        List<Employees> employees = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Employees employee = new Employees(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getInt("age"),
                        resultSet.getString("country"),
                        resultSet.getString("company"),
                        resultSet.getInt("salary"));
                employees.add(employee);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
        return employees;
    }

    public static List<Employees> getEmployeesByCompany(String company) {

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARAMETRIZED)) {
            preparedStatement.setString(1, company);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<Employees> employees = new ArrayList<>();
                while (resultSet.next()) {
                    Employees employee = new Employees(
                            resultSet.getInt(1),// another format
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getInt(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getInt(7)
                    );
                    employees.add(employee);
                }
                return employees;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }


    public static void insertEmployee(Employees employee) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT);
            preparedStatement.setInt(1, employee.id());
            preparedStatement.setString(2, employee.firstName());
            preparedStatement.setString(3, employee.lastName());
            preparedStatement.setInt(4, employee.age());
            preparedStatement.setString(5, employee.country());
            preparedStatement.setString(6, employee.company());
            preparedStatement.setInt(7, employee.salary());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void updateEmployeeFirstName(int id, String newFirstName) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, newFirstName);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static void deleteEmployee(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}
