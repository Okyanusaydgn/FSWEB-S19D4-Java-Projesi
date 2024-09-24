package S19D4.JavaSpringTesting.repository;


import S19D4.JavaSpringTesting.entity.Employee;
import com.sun.jdi.event.StepEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {


    @Query("SELECT e FROM Employee e WHERE e.email=:email")
    Optional<Employee> findByEmail(String email);

    @Query("SELECT e from Employee e WHERE e.salary > :salary ORDER BY e.salary DESC")
    List<Employee> findBySalary(double salary);

    @Query("SELECT e from Employee e ORDER BY e.lastName desc")
    List<Employee> findByOrder();

    @Query("SELECT e FROM Employee e WHERE e.firstName =:firstName")
    List<Employee> findByFirstName(String firstName);

    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary")
    List<Employee> findBySalaryRange(double minSalary, double maxSalary);

    @Query("SELECT e FROM Employee e WHERE e.lastName=:lastName")
    List<Employee> findByLastName(String lastName);

    @Query("SELECT e FROM Employee e WHERE e.salary > :salary")
    List<Employee> findBySalaryGreaterThan(double salary);

    @Query("SELECT e FROM Employee e WHERE e.email LIKE %:email%")
    List<Employee> findByEmailContaining(String email);

    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee> findTopSalaries();
}
