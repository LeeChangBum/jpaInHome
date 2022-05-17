package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Category_Item {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    private Category categoryId;

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    private Item itemId;
}
