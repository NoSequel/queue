package io.github.nosequel.queue.shared.update.sync;

public abstract class DataSyncHandler<T> {

    /**
     * Handle an incoming {@link T} object
     *
     * @param object the object to handle
     */
    public abstract void handleData(T object);

    /**
     * Handle the data casted, this calls
     * the {@link DataSyncHandler#handleData(Object)} method.
     *
     * @param object the object to cast
     */
    public void handleDataCasted(Object object) {
        this.handleData((T) object);
    }

    /**
     * Get the type of the data sync handler
     *
     * @return the type
     */
    public abstract Class<T> getType();

}