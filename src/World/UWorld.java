package World;

import Engine.Base.AActor;
import Engine.Input.MouseInput;
import Engine.Interfaces.IDynamicObject;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UWorld implements IDynamicObject {
    protected final List<AActor> worldActors;
    protected final List<AActor> pendingAddActors;

    public UWorld(IWorldInitializer initializer) {
        worldActors = new ArrayList<>();

        pendingAddActors = new ArrayList<>();
        PreConstruct();
        Init(initializer);
        OnConstruct();
    }

    public UWorld() {
        this(null);
    }

    /**
     * Initialize the world
     */
    private void Init(IWorldInitializer initializer) {
        if (initializer != null) initializer.Initialize(this);
    }

    /**
     * Called before the world initialization
     */
    private void PreConstruct() {

    }

    /**
     * Called after the world initialization
     */
    protected void OnConstruct() {

    }

    public void Tick(double deltaTime) {

        synchronized (pendingAddActors) {
            if (pendingAddActors.size() > 0) {
                worldActors.addAll(pendingAddActors);
                pendingAddActors.clear();
            }
        }

        worldActors.removeIf(A -> !A.IsValid());

        worldActors.forEach(A -> A.Tick(deltaTime));
    }

    @Override
    public void Input(MouseInput Mouse) {
        worldActors.forEach(A -> A.Input(Mouse));
    }

    @Override
    public void Render(Graphics2D graphics2D) {
        worldActors.forEach(A -> A.Render(graphics2D));
    }

    protected void OnUnload() {

    }

    public void AddActor(AActor actor) {
        if (actor == null) throw new IllegalStateException("Cannot add null actors");

        synchronized (pendingAddActors) {
            pendingAddActors.add(actor);
        }
    }


    public void RemoveActor(AActor actor) {
        if (actor == null) throw new IllegalStateException("Cannot remove null actors");
        actor.Destroy();

    }

    protected void Unload() {
        worldActors.forEach(AActor::EndPlay);
        worldActors.clear();
        OnUnload();
    }

    /**
     * Begin play for worlds
     */
    public void BeginPlay() {

    }
}
