package Game.Pieces;

import Engine.Base.AActor;
import GraFX.USpriteSheet;

import java.awt.*;

/**
 * Abstract representation of a chess piece
 */
public abstract class ChessPiece extends AActor {
    private final USpriteSheet sheet;

    public ChessPiece() {
        sheet = getSpriteSheet();
        if (sheet == null) throw new IllegalArgumentException("Sprite sheet of piece cannot be null");
    }

    protected abstract USpriteSheet getSpriteSheet(); // implementation returns the visual of the piece

    @Override
    public final void Render(Graphics2D graphics2D) {
        super.Render(graphics2D);
        graphics2D.drawImage(sheet.GetSpriteSheet(), getPosition().x, getPosition().y, sheet.Width, sheet.Height, null);
    }
}
