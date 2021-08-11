package HelloSpringBlog.SpringBlog;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class User {
    @Id//primaryKey
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //auto_increment

    @Column(nullable = false, length = 30)
    private String username;//아이디

    @Column(nullable = false, length = 100)
    private  String password;

    @Column(nullable = false, length = 50)
    private String email;

    @ColumnDefault("'user'")
    private String role; //Enum을 써야함

    @CreationTimestamp// 시간 자동 입력
    private Timestamp createDate;



}
