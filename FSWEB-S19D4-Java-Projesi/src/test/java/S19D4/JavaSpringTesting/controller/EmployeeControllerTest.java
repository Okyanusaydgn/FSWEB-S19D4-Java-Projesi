package S19D4.JavaSpringTesting.controller;

import S19D4.JavaSpringTesting.entity.Employee;
import S19D4.JavaSpringTesting.services.EmployeeService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest // Bu anatasyon sadece web katmanı yani controller katmanını test etmek için kullanılır. Service,repository gibi diğer katmanları dahil etmez.
public class EmployeeControllerTest {


    @Autowired // MockMvc nesnesini enjekte ederes HTTP istekleri kontrol edilir.
    private MockMvc mockMvc;

    @MockBean // Burada EmployeeService bean'i mocklayarak gerçek bir servis katmanı yerine sahte bir servis kullanırız. Bu sayede servis katmanına bağımlı olmadan test yapılabilir.
    private EmployeeService employeeService;


    @Test // test metodu olduğunu göstermek için bu anatasyonu kullanıyoruz.
    void save() throws Exception{
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmail("okyanus@gmail.com");
        employee.setFirstName("okyanus");
        employee.setLastName("nexus");
        employee.setSalary(20000d);

        when(employeeService.save(employee)).thenReturn(employee); // gerçek save metodunun veritabanına erişmiyor, gerek de olmuyor. Sahte(mock) bir cevap dönüyor

        mockMvc.perform(post("/employee")
                .contentType(MediaType.APPLICATION_JSON) // gönderilen verinin JSON formatında olduğunu söylüyor.
                .content(asJsonString(employee)) // Gönderilen employe nesnesini JSON formatına dönüştürür ve isteğe ekler.
                .accept(MediaType.APPLICATION_JSON)) // sunucudan gelen isteğin JSON formatında kabul eder.
                .andExpect(status().isCreated()) // andExpect - beklenen sonuçları tanımlar. status.isCreated -- HTTP 201 (created) durum kodunu bekler.
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists()) // JSON cevabında id alanın var olup olmadığına kontrol eder.
                .andExpect(MockMvcResultMatchers.jsonPath("$.salary").value(20000d)) // salary alanının 20000d ıknasubu bekler
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L)); // id alanın 1L olmasını bekler.
    }

    private static String asJsonString(Object object) {
        try{
            return new ObjectMapper().writeValueAsString(object); // ObjectMapper sınıfı nesneleri JSON sınıfına dönüştürür.
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e); // Eğer JSON dönüşümünde bir problem yaşanırsa hata gönderiri.
        }
    }

}
