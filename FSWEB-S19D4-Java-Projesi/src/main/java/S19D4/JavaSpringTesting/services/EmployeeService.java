package S19D4.JavaSpringTesting.services;

import S19D4.JavaSpringTesting.entity.Employee;

import java.util.List;

public interface EmployeeService {


    List<Employee> findAll();

    List<Employee> findByFirstName(String firstName);

    List<Employee> findBySalaryRange(double minSalary, double maxSalary);

    List<Employee> findByLastName(String lastName);

    List<Employee> findBySalaryGreaterThan(double salary);

    List<Employee> findByEmailContaining(String email);

    List<Employee> findTopSalaries();

    Employee findById(Long id);

    Employee save(Employee employee);

    void delete(Long id);

    Employee findByEmail(String email);

    List<Employee> findBySalary(double salary);

    List<Employee> findByOrder();

}
