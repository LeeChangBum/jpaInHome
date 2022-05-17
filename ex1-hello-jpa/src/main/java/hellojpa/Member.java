package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.concurrent.locks.Lock;

@Getter
@Setter
@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name="MEMBER_ID")
    private Long id;

    @Column(name="USERNAME")
    private String name;

    @OneToOne
    @JoinColumn(name ="LOCKER_ID")//연관관계의 주인
    private Locker locker;

}
