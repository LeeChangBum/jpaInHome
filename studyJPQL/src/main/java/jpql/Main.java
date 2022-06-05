package jpql;

import javax.persistence.*;
import java.util.List;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory enf= Persistence.createEntityManagerFactory("hello");

        EntityManager em=enf.createEntityManager();//고객의 요청이 올때마다 하나씩 만들어져야한다.

        EntityTransaction tx=em.getTransaction();//모든 데이터변경은 transaction안에서 일어나야 한다.

        tx.begin();
        try {

                Team team=new Team();
                team.setName("teamA");
                em.persist(team);

                Member member = new Member();
                member.setUsername("이창범");
                member.setAge(10);
                member.setTeam(team);

                em.persist(member);

                em.flush();
                em.clear();

               String query="select "+
                                    "case when m.age<=10 then 'student'"+
                                    "     when m.age>=60 then 'elder'"+
                                    "     else 'normal' "+
                                    "end "+
                            "from Member m";
               List<String>result=em.createQuery(query, String.class).getResultList();

               String howMuch=result.get(0);
               System.out.println(howMuch);

            /*자 <Object[]>을 하지 않으면 m,t 두개를 받아야 하니까 List의 제너릭의 default값은 object이므로 object로써 반환을 한다.
            하지만 이는 어차피 object[]로 강제변환해야한다.(배열이 object를 상속받으므로 안해도 실행은 되지만 아무것도 못한다.->부모타입의 변수로 자식타입의 객체를 가리킬순 있지만 자식타입의 객체의 함수 및 변수는 사용할 수 없으므로)
            아래 구문을 실행하면 List타입의 배열을 받는다. 그리고 그 배열의 요소들은 object[], 즉 object타입의 배열이다.
            각각의 object배열의 첫번째 요소는 m, 즉 member이고 두번쨰 요소는 t(team)이다. 하지만 object 타입이므로 member나 team타입으로(자식타입으로) 강제변환해주어야 비로소 사용가능하다.
            설명 그림은 종이자료 한번 보자...
            List<Object[]> result=em.createQuery("select m,t from Member m left (outer) join m.team t on t.name='teamA'")
                    .getResultList();
            Object[] result1=result.get(0);


            Member member1=(Member) result1[0];
            Team team1=(Team) result1[1];
            System.out.println(member1.getAge());
            System.out.println(team1.getName());
            */

            /*밑 방법을 표준으로 사용
            List<MemberDTO> resultList=em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();
            MemberDTO memberDTO=resultList.get(0);
            System.out.println(memberDTO.getUsername());
            */


            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();//닫아주는것이 아주 중요함.
        }

        enf.close();
    }
}
