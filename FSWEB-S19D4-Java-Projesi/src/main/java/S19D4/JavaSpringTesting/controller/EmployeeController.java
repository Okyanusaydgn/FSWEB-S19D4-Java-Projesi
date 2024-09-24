package S19D4.JavaSpringTesting.controller;

import S19D4.JavaSpringTesting.entity.Employee;
import S19D4.JavaSpringTesting.services.EmployeeService;
import com.sun.jdi.event.StepEvent;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee save(@RequestBody Employee employee){
        return employeeService.save(employee);
    }

    @GetMapping
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/{id}")
    public Employee findById(@PathVariable Long id){
        return employeeService.findById(id);
    }

    @GetMapping("/email")
    public Employee findByEmail(@RequestParam String email){
        return employeeService.findByEmail(email);
    }

    @GetMapping("/order")
    public List<Employee> findByOrder(){
        return employeeService.findByOrder();
    }

    @PutMapping("/{id}")
    public  Employee updateEmployee(@PathVariable Long id, @RequestBody Employee employee){
        Employee existingEmployee = employeeService.findById(id);
        if (existingEmployee != null){
            existingEmployee.setFirstName(employee.getFirstName());
            existingEmployee.setLastName(employee.getLastName());
            existingEmployee.setEmail(employee.getEmail());
            existingEmployee.setSalary(employee.getSalary());
            return employeeService.save(existingEmployee);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
    }

    @GetMapping("/firstname")
    public List<Employee> findByFirstName(@RequestParam String firstName){
        return employeeService.findByFirstName(firstName);
    }

    @GetMapping("/salaryrange")
    public List<Employee> findBySalaryRange(@RequestParam double minSalary,@RequestParam double maxSalary){
        return employeeService.findBySalaryRange(minSalary, maxSalary);
    }

    @GetMapping("/lastname")
    public List<Employee> findByLastName(@RequestParam String lastName){
        return employeeService.findByLastName(lastName);
    }

    @GetMapping("/salarygreaterthan")
    public List<Employee> findBySalaryGreaterThan(@RequestParam double salary){
        return employeeService.findBySalaryGreaterThan(salary);
    }

    @GetMapping("/emailcontaining")
    public List<Employee> findByEmailContaining(@RequestParam String email){
        return employeeService.findByEmailContaining(email);
    }

    @GetMapping("/topsalaries")
    public List<Employee> findTopSalaries(){
        return employeeService.findTopSalaries();
    }


}


