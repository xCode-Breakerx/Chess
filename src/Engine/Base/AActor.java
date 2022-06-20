package Engine.Base;

import Engine.Input.MouseInput;
import Engine.Math.Vector2i;

import java.awt.*;

public class AActor extends UObject {
    private boolean bIsPendingKill;
    private final Vector2i position = new Vector2i();

    /**
     * Called when the world has started. This is immediately called if this object
     */
    public void BeginPlay() {

    }


    // do not handle render nor tick by default
    @Override
    public void Tick(double deltaTime) {

    }

    @Override
    public void Input(MouseInput Mouse) {

    }

    @Override
    public void Render(Graphics2D graphics2D) {

    }

    /**
     * Called when the world is unloaded
     */
    public void EndPlay() {

    }

    /**
     * Flags this object as pending kill
     */
    private void MarkPendingKill() {
        bIsPendingKill = true;
    }

    /**
     * Destroy this actor
     */
    public void Destroy() {
        if (!bIsPendingKill) {
            MarkPendingKill();
            OnDestroy();
        }
    }


    /**
     * Event called when an object is about, to be killed
     */
    protected void OnDestroy() {

    }


    /**
     * Tells if this object is alive
     *
     * @return true if object is not pending kill and has not been removed from the world
     */
    public boolean IsValid() {
        return !bIsPendingKill;
    }

    public Vector2i getPosition() {
        return position;
    }
}
