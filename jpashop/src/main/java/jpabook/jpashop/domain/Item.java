package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name="ITEM_ID")
    private Long id;

    private int price;

    private int stockQuantity;

    @OneToMany(mappedBy = "itemId")
    private List<Category_Item> categoryItemitem=new ArrayList<>();
}
