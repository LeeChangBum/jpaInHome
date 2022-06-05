package jpql;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@AllArgsConstructor
@Getter
@Setter
public class MemberDTO {
    private String username;
    private int age;
}
