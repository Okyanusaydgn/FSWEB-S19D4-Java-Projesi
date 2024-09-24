package S19D4.JavaSpringTesting.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data // getter, setter, toString, hashcode,equals yapılarını oluşturur.
@NoArgsConstructor // aşağıda ki tüm alanlara (id, firstname vs. biz değer verebiliriz yani başlangıç değeri vermemize gerek yok.)
@Entity
@Table(name = "employee",schema = "s19d4")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "salary")
    private Double salary;

}
