package io.github.nosequel.queue.shared.model;

public interface Model<T> {

    /**
     * Check if the model's identifier equals to the provided identifiers
     *
     * @param value the identifier to compare it to
     * @return whether it equals to the identifier or not
     */
    boolean equalsToId(T value);

}
