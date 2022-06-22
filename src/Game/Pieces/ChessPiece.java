package Game.Pieces;

import Engine.Base.AActor;
import Game.Enums.ELegalMovement;
import GraFX.USpriteSheet;

import java.awt.*;

/**
 * Abstract representation of a chess piece
 */
public abstract class ChessPiece extends AActor {
    private final USpriteSheet sheet;
    private final boolean bIsBlack;

    public ChessPiece(boolean bIsBlack) {
        this.bIsBlack = bIsBlack;
        sheet = getSpriteSheet();
        if (sheet == null) throw new IllegalArgumentException("Sprite sheet of piece cannot be null");
    }

    protected abstract USpriteSheet getSpriteSheet(); // implementation returns the visual of the piece

    public abstract ELegalMovement getMovementType();

    @Override
    public final void Render(Graphics2D graphics2D) {
        super.Render(graphics2D);
        graphics2D.drawImage(sheet.GetSpriteSheet(), getPosition().x, getPosition().y, sheet.Width, sheet.Height, null);
    }

    public boolean isbIsBlack() {
        return bIsBlack;
    }
}
