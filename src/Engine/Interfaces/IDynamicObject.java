package Engine.Interfaces;

import Engine.Input.MouseInput;

import java.awt.*;

public interface IDynamicObject {

    /**
     * Called every frame on dynamic objects
     *
     * @param deltaTime the time since last frame
     */
    void Tick(double deltaTime);

    /**
     * Input callback for mouse
     *
     * @param Mouse the mouse state
     */
    void Input(MouseInput Mouse);

    /**
     * The render pass, used to render the current entity to the world
     *
     * @param graphics2D the graphics to draw on
     */
    void Render(Graphics2D graphics2D);
}
