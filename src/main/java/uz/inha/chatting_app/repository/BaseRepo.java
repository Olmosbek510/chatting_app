package uz.inha.chatting_app.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import uz.inha.chatting_app.entity.BaseEntity;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

public class BaseRepo<T extends BaseEntity, ID> {
    public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("CHATTING_APP_DB");
    public static final EntityManager ENTITY_MANAGER = ENTITY_MANAGER_FACTORY.createEntityManager();
    private final Class<T> persistenceClass;
    public static void begin(){
        ENTITY_MANAGER.getTransaction().begin();
    }
    public static void commit(){
        ENTITY_MANAGER.getTransaction().commit();
    }
    @SuppressWarnings("unchecked")
    public BaseRepo(){
        this.persistenceClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    public List<T> findAll(){
        return ENTITY_MANAGER.createQuery("from "+ persistenceClass.getSimpleName(), persistenceClass).getResultList();
    }
    public void save(T t){
        begin();
        ENTITY_MANAGER.persist(t);
        commit();
    }

    public void deleteById(ID id){
        begin();
        T t = ENTITY_MANAGER.find(persistenceClass, id);
        ENTITY_MANAGER.remove(t);
        commit();
    }

    public Optional<T> findById(ID id){
        T t = ENTITY_MANAGER.find(persistenceClass, id);
        return Optional.ofNullable(t);
    }
}
