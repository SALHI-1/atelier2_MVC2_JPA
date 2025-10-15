package ma.fstt.services;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
//import ma.fstt.dao.AbstractDAO;

public abstract class AbstractService <T>{

    @PersistenceContext
    protected EntityManager em;
    private final Class<T> ec;

    public AbstractService (Class <T> ec){
        this.ec=ec ;
    }

    public void save(T obj){
        em.persist(obj);
    }

//    public void findAll(){
//
//        em.find();
//
//    }




}
