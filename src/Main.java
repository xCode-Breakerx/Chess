import GUI.Engine;
import Game.ChessWorld;

public class Main {
    public static void main(String[] args) {
        Engine game = new Engine();
        game.LoadWorld(new ChessWorld());
        game.Start();
    }
}
