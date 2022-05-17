package hellojpa;

import hellojpa.inheritance.Movie;
import javassist.bytecode.ExceptionTable;

import javax.persistence.*;
import java.util.concurrent.locks.Lock;

public class JpaMain {
    public static void main(String[] args){
        EntityManagerFactory enf=Persistence.createEntityManagerFactory("hello");

        EntityManager em=enf.createEntityManager();//고객의 요청이 올때마다 하나씩 만들어져야한다.

        EntityTransaction tx=em.getTransaction();//모든 데이터변경은 transaction안에서 일어나야 한다.

        tx.begin();
        try {

            Movie movie=new Movie();
            movie.setName("바람과 함께 사라지다");
            movie.setDirector("이창범");
            movie.setActor("한효주");

            em.persist(movie);
            em.flush();
            em.clear();

            Movie movie1=em.find(Movie.class, movie.getId());
            String actor=movie1.getActor();
            System.out.println(actor);

            tx.commit();
        } catch (Exception e){
            tx.rollback();
        } finally {
            em.close();//닫아주는것이 아주 중요함.
        }

        enf.close();
    }
}
