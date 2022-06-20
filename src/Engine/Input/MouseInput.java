package Engine.Input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Note to the team member doing this class
 * The interface MouseListener and MouseMotionListener may be what we're look for
 *
 * @see java.awt.event.MouseListener
 * @see java.awt.event.MouseMotionListener
 * @see java.awt.event.MouseEvent
 * <p>
 * What we need:
 * Mouse X and Y Position
 * Mouse Button Pressed (RMB or LMB or MMB)[maybe an int?]
 * ...[add more as needed]
 * Methode of MouseListener :
 * public abstract void mouseClicked(MouseEvent e);
 * public abstract void mouseEntered(MouseEvent e);
 * public abstract void mouseExited(MouseEvent e);
 * public abstract void mousePressed(MouseEvent e);
 * public abstract void mouseReleased(MouseEvent e);
 */
public class MouseInput implements MouseListener, MouseMotionListener {
    public static int x, y;
    private int MouseButton = -1;
    private boolean MouseEnter;

    public MouseInput() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //        MouseButton = e.getButton();

    }

    @Override
    public void mousePressed(MouseEvent e) {
        MouseButton = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseButton = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        MouseEnter = true;
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getMouseButton() {
        return MouseButton;
    }

    /**
     * Get MouseEnter
     *
     * @return MouseEnter
     */
    public boolean GetMouseEnter() {
        return MouseEnter;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX();
        y = e.getY();
    }
}