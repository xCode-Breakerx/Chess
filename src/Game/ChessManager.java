package Game;

import Engine.Base.AActor;
import GUI.Engine;
import Game.Blocks.BoardBlock;
import Game.Pieces.APawn;
import Game.Pieces.ChessPiece;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages the chess game.
 * Handles players turn and moves pieces around the board.
 */
public class ChessManager extends AActor {
    private final List<BoardBlock> blocks = new ArrayList<>();
    private final List<ChessPiece> white = new ArrayList<>();
    private final List<ChessPiece> black = new ArrayList<>();

    public ChessManager() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                BoardBlock actor = new BoardBlock((i % 2 == 0) ? (j % 2 == 0) ? ChessConfig.WhiteGridColor : ChessConfig.BlackGridColor : (j % 2 == 0) ? ChessConfig.BlackGridColor : ChessConfig.WhiteGridColor);
                blocks.add(actor);
                actor.getPosition().x = i * ChessConfig.GridSize;
                actor.getPosition().y = j * ChessConfig.GridSize;
            }
        }
        InitializeGame();
        // unsafe to perform actor registration
    }

    @Override
    public void BeginPlay() { // register everything at begin play, during construction the world instance may not be set
        super.BeginPlay();
        ChessWorld chessWorld = Engine.getInstance().getCurrentLevelAs(ChessWorld.class);
        blocks.forEach(chessWorld::AddActor);
        black.forEach(chessWorld::AddActor);
        white.forEach(chessWorld::AddActor);
    }

    /**
     * Initialize the game
     */
    private void InitializeGame() { // create all pieces
        for (BoardBlock block : blocks) {
            if (block.getPosition().y == ChessConfig.GridSize) { // white pawns
                APawn e = new APawn();
                e.getPosition().x = block.getPosition().x;
                e.getPosition().y = block.getPosition().y;
                white.add(e);
            }
            if (block.getPosition().y == ChessConfig.GridSize * 6) { // white pawns
                APawn e = new APawn(true);
                e.getPosition().x = block.getPosition().x;
                e.getPosition().y = block.getPosition().y;
                black.add(e);
            }
        }
    }

//    @Override
//    public void Render(Graphics2D graphics2D) {
//        super.Render(graphics2D);
//        blocks.forEach(B -> B.Render(graphics2D));
//        white.forEach(B -> B.Render(graphics2D));
//        black.forEach(B -> B.Render(graphics2D));
//
//    }
}
