package jpabook.jpashop.domain;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {
    public static void main(String[] args){
        EntityManagerFactory EMF= Persistence.createEntityManagerFactory("hello");
        EntityManager EM=EMF.createEntityManager();
        EntityTransaction tx=EM.getTransaction();

        tx.begin();




        tx.commit();

        EM.close();
        EMF.close();

    }

}
