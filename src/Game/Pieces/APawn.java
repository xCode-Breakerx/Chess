package Game.Pieces;

import Game.Enums.ELegalMovement;
import GraFX.USpriteSheet;

public class APawn extends ChessPiece {

    public APawn() {
        super(false);
    }

    public APawn(boolean bIsBlack) {
        super(bIsBlack);
    }

    @Override
    protected USpriteSheet getSpriteSheet() {
        return new USpriteSheet(isbIsBlack() ? "images/Pn.png" : "images/Pb.png");
    }

    @Override
    public ELegalMovement getMovementType() {
        return ELegalMovement.ELEGAL_MOVEMENT_FRONTAL;
    }
}
