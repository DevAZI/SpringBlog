package HelloSpringBlog.SpringBlog.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;


import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;//섬머노트 라이브러리 사용(  <html> )

    //@ColumnDefault("0")
    private int count;

    //FetchType.Eager = 즉시 // .lazy = 지연
    @ManyToOne(fetch = FetchType.LAZY) //Many = Board , One = user >> 한명(user)이 여러개의 개시글을 사용할 수 있음
    @JoinColumn(name = "userId")
    private User user; //db는 object저장 불가능 >  FK이용해야함, > 자바는 object가능 but db는 불가능

    @OneToMany(mappedBy = "board")
    private List<Reply> reply;

    @CreationTimestamp
    private Timestamp createDate;
}
