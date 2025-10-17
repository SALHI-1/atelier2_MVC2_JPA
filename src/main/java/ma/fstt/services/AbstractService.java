package ma.fstt.services;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;
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

/*    public T findById(){



        return ;
    }*/
public List<T> findAll() {
    // Création d'une requête JPQL générique pour récupérer tous les objets de type T
    TypedQuery<T> query = em.createQuery("SELECT e FROM " + ec.getSimpleName() + " e", ec);
    return query.getResultList();
}


    public T findById(Long id) {
        try {
            TypedQuery<T> query = em.createQuery(
                    "SELECT e FROM " + ec.getSimpleName() + " e WHERE e.id = :id",
                    ec
            );
            query.setParameter("id", id);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null; // aucun résultat trouvé
        }
    }




}
