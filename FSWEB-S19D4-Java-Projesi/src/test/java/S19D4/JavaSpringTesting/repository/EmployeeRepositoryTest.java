package S19D4.JavaSpringTesting.repository;


import S19D4.JavaSpringTesting.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest // servise,controller,repository gerçek versiyonunu kullanır. yani test sırasında repository nin gerçek işlevini kullanarak veritabanına erişim sağlar ve sorgulama yapar.
public class EmployeeRepositoryTest {

    private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRepositoryTest(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @BeforeEach // testten önce yapılan hazırlıkları belirtir
    void setUp(){
        Employee employee1 = new Employee();
        employee1.setFirstName("frank");
        employee1.setLastName("frank1");
        employee1.setEmail("frank@gmail.com");
        employee1.setSalary(1000d);

        Employee employee2 = new Employee();
        employee2.setFirstName("fatma");
        employee2.setLastName("fatma1");
        employee2.setEmail("fatma@gmail.com");
        employee2.setSalary(2000d);

        Employee employee3 = new Employee();
        employee3.setFirstName("kerem");
        employee3.setLastName("kerem1");
        employee3.setEmail("kerem@gmail.com");
        employee3.setSalary(3000d);

        List<Employee> employees = new ArrayList<>();
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employeeRepository.saveAll(employees);
    }

    @AfterEach // testten sonra yapılacak işlemi yani resetleme / temizleme yapar.
    void tearDown(){
        employeeRepository.deleteAll();
    }
    // static kullanılmasının sebebi metodun sadece bir kez çalıştığını belirtir ve sınıf düzeyinde çalıştığını yani örneği oluşturulmadan çalışır.
    // void herhangi bir değer döndürmez. Dönüş tipi yoktur. Void sadece bir işi yapar ve geriye herhangi bir sonuç döndürmez.
    @BeforeAll
    static void beforeAll(){
        System.out.println("Before All");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("After All");
    }


    @Test
    @DisplayName(value = "find employee by email user tests") // test çalıştırıldığında açıklama yapılması için kullanılır.
    void findByEmail(){
        String nonExistEmail = "ahmet@gmail.com"; // veritabanında bulunmayan bir mail yazılmış
        Optional<Employee> employeeOptional = employeeRepository.findByEmail(nonExistEmail); // veritabanında bu e-posta ile eşleşen bir çalışan aranır. Eğer böyle bir çalışan yoksa Optional.Empty() döner.
        assertEquals(Optional.empty(),employeeOptional); // bulunmayan kullanıcı için Optional.empty yani veri yok demesi lazım. Eğer yoksa hata döner.

        String existEmail = "kerem@gmail.com"; // veritabanında tanımlanmış bir mail yazılmış.
        Optional<Employee> employeeOptionalExist = employeeRepository.findByEmail(existEmail);  // veritabanında bu maili bulduğu zaman Optional içeren bir Employee nesnesi döner.
        assertNotNull(employeeOptionalExist.get()); // eğer veritabanında bir çalışan bulundu ise null olmayacaktır.
        assertEquals("kerem",employeeOptionalExist.get().getFirstName()); // çalışanın adı "kerem" olmalıdır.
        assertEquals(3000d,employeeOptionalExist.get().getSalary()); // çalışanın maaşı "3000" olmalıdır.
    }


    @Test
    void findBySalary(){
        List<Employee> employees = employeeRepository.findBySalary(1800);
        assertEquals(2,employees.size());
    }

    @Test
    void findByOrder(){
        List<Employee> employees = employeeRepository.findByOrder();
        assertEquals(3,employees.size());
        assertEquals("kerem",employees.get(0).getFirstName());
        assertEquals("fatma",employees.get(1).getFirstName());
    }


}
