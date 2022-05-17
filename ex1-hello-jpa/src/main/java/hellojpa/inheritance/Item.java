package hellojpa.inheritance;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@DiscriminatorColumn//DTYPE(서브타입이 어떤건지 알려줌)을 만들어줌
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Item {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private int price;

}
//Join 전략->만약 서브타입테이블에서 find할 시 알아서 ITEM 테이블을 INNER JOIN해서 가지고 온다.(한마디로 무조건 가져옴)
//SINGLE_TABLE->그냥 하나의 테이블에 모든 컬럼이 있다. DTYPE으로만 어떤 서브타입인지 구분