import model.Employees;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

       try {
           // Insert a new employee
           int id = 6;
           String company = "TechCorp";
           Employees newEmployee = new Employees(id, "Alice", "Johnson", 30, "USA", company, 90000);
           DatabaseOperations.insertEmployee(newEmployee);
           System.out.println("Inserted new employee: " + newEmployee);

           // Select and print all employees
           List<Employees> employeesList = DatabaseOperations.getEmployees();
           System.out.println("List of all employees:");
           for (Employees employee : employeesList) {
               System.out.println(employee);
           }

           // Select and print employees by company
           List<Employees> employeesByCompany = DatabaseOperations.getEmployeesByCompany(company);
           System.out.println("Employees working at " + company + ":");
           for (Employees employee : employeesByCompany) {
               System.out.println(employee);
           }

           // Update the first name of the employee with ID 1
           DatabaseOperations.updateEmployeeFirstName(1, "Rob");
           System.out.println("Updated employee with ID 1: " + DatabaseOperations.getEmployees());

           // Delete the employee with ID 1
           DatabaseOperations.deleteEmployee(id);
           System.out.println("Deleted employee with ID: " + id);
       } finally {
           // Close the connection
           try {
               DatabaseConnection.closeConnection();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }


    }
}
