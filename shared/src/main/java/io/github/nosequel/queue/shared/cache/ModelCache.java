package io.github.nosequel.queue.shared.cache;

import io.github.nosequel.queue.shared.model.Model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public abstract class ModelCache<U, T extends Model<U>> {

    private final Set<T> models = new HashSet<>();

    /**
     * Add a new model to the cache
     *
     * @param model the model to add to the cache
     */
    public void addCache(T model) {
        this.models.add(model);
    }

    /**
     * Find a value inside of the {@link ModelCache#models} set.
     * <p>
     * This method loops through all the cached models until
     * it finds a model which returns true whenever the {@link Model#equalsToId(Object)}
     * method returns true, which means the provided identifier equals the object's identifier.
     *
     * @param value the identifier to find the model by
     * @return the optional of the model
     */
    public Optional<T> find(U value) {
        return this.models.stream()
                .filter(model -> model.equalsToId(value))
                .findFirst();
    }
}