package GUI;

import Engine.Input.MouseInput;
import Game.ChessConfig;
import World.UWorld;
import World.UWorldManager;

import javax.swing.*;
import java.awt.*;

public class Engine extends JPanel implements Runnable {

    public static Engine instance;
    private final MouseInput mouseInput;
    private Thread gameThread;
    private boolean bIsRunning;
    private Graphics2D graphics2D;
    private final UWorldManager manager;
    private final int GAME_WIDTH;
    private final int GAME_HEIGHT;

    public Engine() {
        instance = this;
        GAME_WIDTH = ChessConfig.GridSize * 8;
        GAME_HEIGHT = ChessConfig.GridSize * 8;
        setSize(WIDTH, HEIGHT);
        JFrame jFrame = new JFrame() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(GAME_WIDTH + getInsets().left + getInsets().right, GAME_HEIGHT + getInsets().top + getInsets().bottom);
            }
        };

        jFrame.pack();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setResizable(false);
        jFrame.add(this);
        manager = new UWorldManager();
        mouseInput = new MouseInput();
        addMouseListener(mouseInput);
        addMouseMotionListener(mouseInput);
    }

    public synchronized void LoadWorld(UWorld world) {
        manager.LoadWorld(world);
    }

    public synchronized UWorld getCurrentLevel() {
        return UWorldManager.getWorld();
    }

    public synchronized <T> T getCurrentLevelAsUnchecked(Class<T> clazz, UWorld w) {
        return clazz.cast(w);
    }

    public <T extends UWorld> T getCurrentLevelAs(Class<? extends T> clazz) {
        UWorld world = UWorldManager.getWorld();
        if (world == null) return null;
        if (clazz.isAssignableFrom(world.getClass())) {
            return getCurrentLevelAsUnchecked(clazz, world);
        }
        return null;
    }


    /**
     * Attempts to start the engine
     *
     * @throws IllegalStateException if the engine is already running
     */
    public synchronized void Start() throws IllegalStateException {

        if (gameThread != null) throw new IllegalStateException("The game is already running");

        gameThread = new Thread(this);
        gameThread.start();
        bIsRunning = true;
    }

    /**
     * Get the engine instance
     *
     * @return the engine instance or null if the engine isn't running
     */
    public synchronized static Engine getInstance() {
        return instance;
    }

    /**
     * Marks the engine as pending kill
     *
     * @param bForceStop tells if the engine should kill the main thread
     */
    public void Stop(boolean bForceStop) {
        if (gameThread == null) throw new IllegalStateException("The engine isn't running.");

        if (!bIsRunning) {
            System.out.println("Skipped engine shutdown, engine already abandoned");
            return;
        }

        if (gameThread.isAlive()) {
            if (bForceStop) {
                gameThread.interrupt();
            } else {
                try {
                    gameThread.join();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Engine abandoned");
        }
        instance = null;
    }

    @Override
    public void run() {

        Init();
        try {
            GuardedRun();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.out.println("The engine has crashed, abandoning the engine");
            e.printStackTrace();
        }
        CleanUp();

    }


    /**
     * Guarded run function, Handles engine tick and render
     */
    private void GuardedRun() {
        double curTime;
        double lastTime = System.currentTimeMillis();
        while (bIsRunning) {
            curTime = System.currentTimeMillis();
            double delta = curTime - lastTime;
            lastTime = curTime;
            manager.Input(mouseInput);
            Tick(delta);
            repaint();
            try {
                Thread.yield();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Render the engine graphics
     *
     * @param graphics2D the graphics to draw on
     */
    private void Render(Graphics2D graphics2D) {
        manager.Render(graphics2D);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Render((Graphics2D) g);
    }

    /**
     * Function called every frame
     *
     * @param deltaTime the time since last tick
     */
    private void Tick(double deltaTime) {
        manager.Tick(deltaTime);
    }

    /**
     * Initialize the engine
     */
    private void Init() {


    }

    private void CleanUp() {

    }
}
