package com.jaly.ddd.object_factory_jpa.db.jpa;

import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

/**
 * Utility methods to help write cleaner transaction code.
 *
 */
public class TransactionUtils {

    /**
     * Get an object from DB by identifier
     * @param <Entity>
     * @param <Identifier>
     */
    public interface Lookup<Entity, Identifier> {

        Entity lookup(EntityManager entityManager, Identifier identifier);

        /**
         * default method to look up an entity
         * @param identifier the identifier of object
         * @return
         */
        default Optional<Entity> lookupOptional(EntityManager entityManager, Identifier identifier) {
            return Optional.ofNullable(lookup(entityManager, identifier));
        }

        /**
         * look up all entities
         * @param entityManager
         * @param identifier
         * @return
         */
        List<Entity> lookupAll(EntityManager entityManager, Identifier identifier);
    }
}
