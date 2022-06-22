package Game;

import World.UWorld;

/**
 * Chess world
 */
public class ChessWorld extends UWorld {
    private ChessManager chessManager;

    @Override
    protected void OnConstruct() {
        chessManager = new ChessManager();
        AddActor(chessManager);
    }

    public ChessManager getChessManager() {
        return chessManager;
    }
}
