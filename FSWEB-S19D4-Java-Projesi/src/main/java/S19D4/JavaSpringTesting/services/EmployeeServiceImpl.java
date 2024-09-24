package S19D4.JavaSpringTesting.services;

import S19D4.JavaSpringTesting.entity.Employee;
import S19D4.JavaSpringTesting.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;


    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> findByFirstName(String firstName) {
        return employeeRepository.findByFirstName(firstName);
    }

    @Override
    public List<Employee> findBySalaryRange(double minSalary, double maxSalary) {
        return employeeRepository.findBySalaryRange(minSalary, maxSalary);
    }

    @Override
    public List<Employee> findByLastName(String lastName) {
        return employeeRepository.findByLastName(lastName);
    }

    @Override
    public List<Employee> findBySalaryGreaterThan(double salary) {
        return employeeRepository.findBySalaryGreaterThan(salary);
    }

    @Override
    public List<Employee> findByEmailContaining(String email) {
        return employeeRepository.findByEmailContaining(email);
    }

    @Override
    public List<Employee> findTopSalaries() {
        return employeeRepository.findTopSalaries();
    }

    @Override
    public Employee findById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id));
    }

    /*
    *
    @Override
    public Employee findById(Long id) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(id);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }
        return null;
    }
    *
    * */

    @Override
    public Employee save(Employee employee) {
        Employee foundByEmail = findByEmail(employee.getEmail());
        if(foundByEmail !=null){
            throw new IllegalArgumentException("Email is already in use");
        }
      return   employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        Employee employee = findById(id);
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found with id: " + id);
        }
        employeeRepository.delete(employee);
    }

    @Override
    public Employee findByEmail(String email) {
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(email);
        if(employeeOptional.isPresent()){
            return employeeOptional.get();
        }
        return null;
    }

    @Override
    public List<Employee> findBySalary(double salary) {
        return employeeRepository.findBySalary(salary);
    }

    @Override
    public List<Employee> findByOrder() {
        return employeeRepository.findByOrder();
    }


}
