package Game.Pieces;

import GraFX.USpriteSheet;

public class APawn extends ChessPiece {

    @Override
    protected USpriteSheet getSpriteSheet() {
        return new USpriteSheet("images/Cb.png");
    }
}
