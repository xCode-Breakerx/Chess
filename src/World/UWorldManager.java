package World;

import Engine.Input.MouseInput;
import Engine.Interfaces.IDynamicObject;

import java.awt.*;

public class UWorldManager implements IDynamicObject {
    private static UWorld activeWorld;

    /**
     * Loads a given user world
     *
     * @param world the world to be loaded
     */
    public void LoadWorld(UWorld world) {
        if (world == null) throw new IllegalArgumentException("Cannot load null world");
        UnloadCurrentWorld();
        activeWorld = world;
        activeWorld.BeginPlay();
    }

    /**
     * Unloads the current world (if any active)
     */
    public void UnloadCurrentWorld() {
        if (activeWorld != null) {
            activeWorld.Unload();
            activeWorld = null;
        }
    }

    /**
     * Get the current loaded world
     *
     * @return the current {@link UWorld}
     */
    public static UWorld getWorld() {
        return activeWorld;
    }


    @Override
    public void Tick(double deltaTime) {
        if (activeWorld != null) activeWorld.Tick(deltaTime);
    }

    @Override
    public void Input(MouseInput Mouse) {
        if (activeWorld != null) activeWorld.Input(Mouse);
    }

    @Override
    public void Render(Graphics2D graphics2D) {
        if (activeWorld != null) activeWorld.Render(graphics2D);
    }
}
