package io.github.nosequel.queue.shared.cache;

import io.github.nosequel.queue.shared.model.Model;
import lombok.Getter;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Getter
public abstract class ModelCache<U, T extends Model<U>> {

    public final Set<T> models = new HashSet<>();

    /**
     * Check if the model cache has a model cached.
     *
     * @param model the model to check if it's cached
     * @return whether the provided model is already cached or not
     */
    public boolean hasModel(T model) {
        return this.models.contains(model);
    }

    /**
     * Add a new model to the cache
     *
     * @param model the model to add to the cache
     */
    public void addModel(T model) {
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