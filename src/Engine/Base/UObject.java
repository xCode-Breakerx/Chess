package Engine.Base;

import Engine.Interfaces.IDynamicObject;

import java.util.UUID;

public abstract class UObject implements IDynamicObject {
    /**
     * The unique GUID of this object
     */
    private final UUID id = UUID.randomUUID();

    /**
     * Retrieve the unique GUID of this object
     *
     * @return the GUID of this object
     */
    public UUID getId() {
        return id;
    }
}
