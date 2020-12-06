package com.jrstep.dao;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class Repo {
    private EntityManager em;
    @Transactional
    public Object get(){
        Object obj = null;
        Query q = em.createNativeQuery("select * from Orgnsm ");
        q.executeUpdate();
        List<Object> ol = q.getResultList();
        for(Object o : ol){
            System.out.println(o);
        }
        return obj;
    }
}
