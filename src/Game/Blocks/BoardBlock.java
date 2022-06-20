package Game.Blocks;

import Engine.Base.AActor;
import Engine.Input.MouseInput;
import Game.ChessConfig;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A board block
 */
public class BoardBlock extends AActor {
    private Color color = ChessConfig.WhiteGridColor;
    private double LastTimeSelected = System.nanoTime();
    private boolean bIsClicked;
    private final int padding = 2;
    private final float dampingFactor = 0.3f;


    public BoardBlock() {
    }

    @Override
    public void Input(MouseInput Mouse) {
        super.Input(Mouse);
        if (Mouse.getMouseButton() == MouseEvent.BUTTON1 && ((System.nanoTime() - LastTimeSelected) / 1_000_000_000.0f) > dampingFactor) {
            if (Mouse.getX() > getPosition().x &&
                    Mouse.getX() < getPosition().x + ChessConfig.GridSize &&
                    Mouse.getY() > getPosition().y &&
                    Mouse.getY() < getPosition().y + ChessConfig.GridSize
            ) {
                LastTimeSelected = System.nanoTime();
                bIsClicked ^= true;
            }
        }
    }

    public BoardBlock(Color color) {
        this.color = color;
    }

    @Override
    public void Render(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillRect(getPosition().x, getPosition().y, ChessConfig.GridSize, ChessConfig.GridSize);
        if (bIsClicked) {
            graphics2D.setColor(Color.red);
            graphics2D.setStroke(new BasicStroke(2.5f));
            graphics2D.drawRect(getPosition().x + padding, getPosition().y + padding, ChessConfig.GridSize - padding * 2, ChessConfig.GridSize - padding * 2);
        }
    }
}
