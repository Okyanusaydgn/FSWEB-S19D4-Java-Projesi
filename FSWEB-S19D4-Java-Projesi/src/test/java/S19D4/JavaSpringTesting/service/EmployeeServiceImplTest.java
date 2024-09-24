package S19D4.JavaSpringTesting.service;


import S19D4.JavaSpringTesting.entity.Employee;
import S19D4.JavaSpringTesting.repository.EmployeeRepository;
import S19D4.JavaSpringTesting.repository.EmployeeRepositoryTest;
import S19D4.JavaSpringTesting.services.EmployeeService;
import S19D4.JavaSpringTesting.services.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.BDDMockito.given;

import java.util.Optional;

import static org.awaitility.Awaitility.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;



@SpringBootTest
@ExtendWith(MockitoExtension.class) // ExtendWith sayesinde Mockito framework'ünü bu test sınıfına dahil ediyoruz. MockitoExtension Mockito özelliklerini içeri aktarıyor.
public class EmployeeServiceImplTest {


    @Mock // bunu kullanarak Gerçek veri tabanı yerine simüle edilmiş test kullanılır yani sahte veritabanı diyebiliriz.
    private EmployeeRepository employeeRepository;

    private EmployeeService employeeService;

    @BeforeEach
    void setUp() {
        employeeService = new EmployeeServiceImpl(employeeRepository); // burada kullanılan employeeRepository yukarıda mock tarafından simüle edilmiş "employeeRepository" den verileri alıyor.
    }

    @Test
    void findAll() {
        employeeService.findAll();
        verify(employeeRepository).findAll(); // buradaki amaç; employeeServiceImpl sınıfında findAll() metodunun gerçekten de repository de ki findAll metodunu çağırıp çağırmadığı kontrol edilir.
    }



    @Test
    void findByOrder() {
        employeeService.findByOrder();
        verify(employeeRepository).findByOrder();
    }


    @Test
    void isSaveSucceed(){
        Employee employee = new Employee();
        employee.setEmail("ali@gmail.com");
        employee.setFirstName("okyanus");
        employee.setLastName("aydogan");
        given(employeeRepository.findByEmail("deneme@gmail.com")).willReturn(Optional.empty());
        given(employeeRepository.save(employee)).willReturn(employee);

        Employee saved = employeeService.save(employee);
        assertNotNull(saved);
    }

}
