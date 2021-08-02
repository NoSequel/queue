package io.github.nosequel.queue.shared.update;

import lombok.Getter;

@Getter
public abstract class UpdateData<T> {

    private final long updateDate;

    /**
     * Constructor to make a new {@link UpdateData} object.
     * <p>
     * This constructor declares the {@link UpdateData#updateDate}
     * as {@code System.currentTimeMillis()}, so the update date will
     * always be the date of whenever the class gets constructed.
     * </p>
     */
    public UpdateData() {
        this.updateDate = System.currentTimeMillis();
    }

    /**
     * Check if the update data is equal to a
     * provided {@link T} object.
     *
     * @param data the data to match against the update data
     * @return whether it's equal to the update data or not
     */
    public abstract boolean isEqualTo(T data);

    /**
     * Update the provided {@link T} object to
     * the current update data object.
     *
     * @param object the object to update it to
     */
    public abstract void updateTo(T object);

}