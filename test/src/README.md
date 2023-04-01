## Câu 1
Thuộc tính name trong annotation @Entity khác với thuộc tính name trong @Table như thế nào?

Trả lời:
```Java
@Entity
@Table(name = "user")
public class User {
    ...
}
```
@Table(name = "user") tương ứng với name của Table là "user" trong database được chỉ định cho Entity tương ứng là User. 
Nếu không được chỉ định, tên thực thể sẽ được dùng làm tên bảng tương ứng đoạn code dưới.
```Java
@Entity(name = "user")
public class User {
    ...
}
```
@Entity(name = "user") tương ứng với "user" là tên của Entity. Nó là một bắt buộc để đánh dấu một đối tượng là một thực thể và để JPA biết cách ánh xạ đối tượng "User" vào cơ sở dữ liệu.

## Câu 2
Để debug câu lệnh SQL mà Hibernate sẽ sinh ra trong quá trình thực thi, cần phải bổ sung lệnh nào vào file application.properties?

Trả lời:
```
spring.jpa.show-sql=true
```
Thuộc tính "spring.jpa.show-sql" được đặt là "true" để hiển thị các câu lệnh SQL được sinh ra bởi Hibernate trong quá trình thực thi.

## Câu 3
Annotation @Column dùng để bổ sung tính chất cho cột ứng với một thuộc tính.
 - Tham số nào trong @Column sẽ đổi lại tên cột nếu muốn khác với tên thuộc tính? 
 - Tham số nào chỉ định yêu cầu duy nhất, không được trùng lặp dữ liệu?
 - Tham số nào buộc trường không được null?

Trả lời:
- Tham số "name" trong @Column được sử dụng để chỉ định tên cột trong bảng cơ sở dữ liệu. Nếu không được chỉ định, tên cột sẽ mặc định là tên thuộc tính.
- Tham số "unique" trong @Column được sử dụng để chỉ định rằng giá trị của cột phải là duy nhất. Nếu giá trị của thuộc tính này là true, JPA sẽ tạo một ràng buộc duy nhất trên cột tương ứng trong bảng cơ sở dữ liệu.
- Tham số "nullable" trong @Column được sử dụng để chỉ định rằng giá trị của cột có thể là null hay không. Nếu giá trị của thuộc tính này là false, JPA sẽ tạo một ràng buộc NOT NULL trên cột tương ứng trong bảng cơ sở dữ liệu.
Ví dụ:
```Java
@Column(name = "user_name", unique = true, nullable = false)
private String name;
```

## Câu 4
Có 2 sự kiện mà JPA có thể bắt được, viết logic bổ sung

Ngay trước khi đối tượng Entity lưu xuống CSDL (ngay trước lệnh INSERT)
Ngay trước khi đối tượng Entity cập nhật xuống CSDL (ngay trước lệnh UPDATE)
Vậy 2 annotation này là gì?

Trả lời:

2 annotation này: @PrePersist (ngay trước lệnh INSERT) & @PreUpdate (ngay trước lệnh UPDATE)
```Java
@Entity
public class User {
   // ...
    
    @PrePersist
    protected void onCreate() {
        // viết logic bổ sung
        // gán lại giá trị createAt trước khi INSERT.
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        // viết logic bổ sung
        // Cập nhật giá trị cho updatedAt trước khi UPDATE.
        updatedAt = LocalDateTime.now();
    }
   // ...
}
```
## Câu 5
JpaRepository là một interface có sẵn trong thư viện JPA, nó cũng cấp các mẫu hàm thuận tiện cho thao tác dữ liệu. Cụ thể JpaRepository kế thừa từ interface nào?

trả lời:

JpaRepository cung cấp toàn bộ phương thức của PagingAndSortingRepository và CrudRepository:
- CrudRepository cung cấp các hàm CRUD cơ bản.
- PagingAndSortingRepository extends CrudRepository, bổ sung phương thức về việc phân trang và sắp xếp kết quả tìm kiếm.
- JpaRepository extends PagingAndSortingRepository, cung cấp thêm các hàm cho bộ chuẩn JPA như là xóa theo lô, hoặc đồng bộ từ Persistence Context vào cơ sở dữ liệu.
3 loại repository này đều extends từ một interface chung là Repository.

## Câu 6
Hãy viết khai báo một interface repository thao tác với một Entity tên là Post, kiểu dữ liệu trường Identity là long, tuân thủ interface JpaRepository.
Trả lời:
```Java
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

}
```
- Post: Tên Entity.
- Long: Kiểu dữ liệu của trường ID trong Entity.

## Câu 7
Khi đã chọn một cột là Identity dùng @Id để đánh dấu, thì có cần phải dùng xác định unique dùng annotation @Column(unique=true) không?

Trả lời:

@Id được sử dụng để đánh dấu trường là khóa chính của Entity. Các giá trị trong trường @Id phải là duy nhất và không null. Vì vậy, khi một trường được đánh dấu bằng @Id, JPA sẽ đảm bảo rằng nó là duy nhất mà không cần sử dụng @Column(unique=true) để xác định nữa.

## Câu 8
Giả sử có 1 class Employee với các fields sau {id, emailAddress, firstName, lastName}. Hãy viết các method trong interface EmployeeRespository để :

Tìm tất cả các Employee theo emailAddress và lastName
Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
Tìm tất cả các Employee theo fistName không phân biệt hoa thường

Trả lời:
```Java
package com.example.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email_address")
    private String emailAddress;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

}
```
```Java
package com.example.test.repository;

import com.example.test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Tìm tất cả các Employee theo emailAddress và lastName
    List<Employee> findByEmailAddressAndLastName(String emailAddress, String lastName);
    //Tìm tất cả các Employee khác nhau theo firstName hoặc lastName
    List<Employee> findByFirstNameOrLastName(String firstName, String lastName);
    //Tìm tất cả các Employee theo lastName và sắp xếp thứ tự theo firstName tăng dần
    List<Employee> findByLastNameOrderByFirstNameAsc(String lastName);
    //Tìm tất cả các Employee theo fistName không phân biệt hoa thường
    List<Employee> findByFirstNameIgnoreCase(String firstName);
}
```

## Câu 9
Hãy nêu cách sử dụng của @NamedQuery và @Query. Cho ví dụ

Trả lời:

@NamedQuery là một annotation được định nghĩa trên entity và cho phép định nghĩa một câu truy vấn có thể được tái sử dụng nhiều lần trong code của bạn. Câu truy vấn được định nghĩa trong @NamedQuery được đặt tên và có thể được gọi trong EntityManager bằng tên đó.
```java
@Entity
@NamedQuery(
        name = "Employee.findAllByLastName",
        query = "SELECT e FROM Employee e WHERE e.lastName = :lastName"
)
public class Employee {
    // ...
}
```
```java
package com.example.test.service;

import com.example.test.entity.Employee;
import com.example.test.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

@AllArgsConstructor
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    EntityManagerFactory emf;
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public Employee getEmployeeByLastName(String lastName){
        Query namedQuery = getEntityManager().createNamedQuery("Employee.findEmployeeByLastName");
        namedQuery.setParameter("lastName", lastName);
        return (Employee) namedQuery.getSingleResult();
    }
}
```
@Query là một annotation được định nghĩa trên một repository và cho phép bạn định nghĩa một câu truy vấn tùy ý để thực thi trên CSDL. Câu truy vấn được định nghĩa trong @Query được viết bằng JPQL hoặc SQL.
```java
package com.example.test.repository;

import com.example.test.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query("SELECT e FROM Employee e WHERE e.emailAddress = :emailAddress AND e.lastName = :lastName")
    List<Employee> findByEmailAndLastName(String emailAddress, String lastName);
}
```

## Câu 10
Hãy nêu 1 ví dụ sử dụng sorting và paging khi query đối tượng Employee ở trên

Trả lời:
```java
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Page<Employee> findByOrderByLastNameAsc(Pageable pageable);
}
```
```java
public class EmployeeService {
    
    public List<Employee> getEmployeePagination(int page, int pageSize, String sort){
        //page = 0 > lay trang dau tien
        //pageSize = 10 > 10 employee
        //sort = "laseName > sort by last name tang dan 
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(sort).ascending());
        Page<Employee> page = employeeRepository.findByOrderByLastNameAsc(pageable);
        return page.getContent();
    }
}
```

## Câu 11
Có 3 Entity Product.java và Category.java và Tag.java

Hãy bổ sung định nghĩa quan hệ One to Many giữa bảng Category (One) – Product (Many). Chú ý khi một Category xoá, thì không được phép xoá Product, mà chỉ set thuộc tính Category của Product là null.
Hãy bổ sung định nghĩa quan hệ Many to Many giữa bảng Tag(Many) – Product(Many).

Trả lời:
```java
package com.example.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
}
```
```java
package com.example.test.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
```