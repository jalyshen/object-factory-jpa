package com.jaly.ddd.object_factory_jpa.db.jpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.hibernate.LockMode;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * JPA utility to help transaction operations
 */
@Component
public class JPAUtil {

    public static class Equals<Entity, Identifier> implements TransactionUtils.Lookup<Entity, Identifier> {

        /**
         * The name is one of the entity field name
         */
        private final String name;

        /**
         * the entity class
         */
        private final Class<Entity> cls;

        public Equals(Class<Entity> cls, String name) {
            this.cls = cls;
            this.name = name;
        }

        @Override
        public Entity lookup(EntityManager entityManager, Identifier identifier) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<Entity> cr = cb.createQuery(cls);
            Root<Entity> entityRoot = cr.from(cls);
            cr.select(entityRoot);
            Predicate predicate = cb.equal(entityRoot.get(name), identifier);
            return lookupOne(entityManager, cr, this.cls, predicate);
        }

        @Override
        public List<Entity> lookupAll(EntityManager entityManager, Identifier identifier) {
            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            return lookupMany(entityManager, cb, this.cls, null);
        }
    }

    /**
     *
     * @param entityManager
     * @param criteriaQuery
     * @param cls
     * @param predicates
     * @return
     * @param <T>
     */
    public static <T> T lookupOne(EntityManager entityManager,
                                  CriteriaQuery criteriaQuery,
                                  Class<T> cls,
                                  Predicate... predicates) {
        criteriaQuery.where(predicates);
        TypedQuery<T> query = entityManager.createQuery(criteriaQuery);
        return query.getSingleResult();
    }

    /**
     *
     * @param entityManager
     * @param criteriaBuilder
     * @param cls
     * @param lockMode
     * @param predicates
     * @return
     * @param <T>
     */
    public static <T> List<T> lookupMany(EntityManager entityManager,
                                         CriteriaBuilder criteriaBuilder,
                                         Class<T> cls, LockMode lockMode,
                                         Predicate... predicates) {
        return List.of();
    }
}

