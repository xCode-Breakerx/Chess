package Game;

import Game.Blocks.BoardBlock;
import World.UWorld;

public class ChessWorld extends UWorld {

    @Override
    protected void OnConstruct() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BoardBlock actor = new BoardBlock((i % 2 == 0) ? (j % 2 == 0) ? ChessConfig.WhiteGridColor : ChessConfig.BlackGridColor : (j % 2 == 0) ? ChessConfig.BlackGridColor : ChessConfig.WhiteGridColor);
                AddActor(actor);
                actor.getPosition().x = i * ChessConfig.GridSize;
                actor.getPosition().y = j * ChessConfig.GridSize;
            }
        }
    }
}
