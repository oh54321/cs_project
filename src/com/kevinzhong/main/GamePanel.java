package com.kevinzhong.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import com.kevinzhong.entity.Entity;
import com.kevinzhong.entity.Player;
import com.kevinzhong.handlers.Camera;
import com.kevinzhong.handlers.KeyInput;
import com.kevinzhong.handlers.MouseInput;
import com.kevinzhong.handlers.MouseWheelInput;
import com.kevinzhong.state.GameState;
import com.kevinzhong.state.State;

public class GamePanel extends Canvas implements Runnable {

    private static JFrame mainJFrame;
    private static GraphicsDevice graphicsDevice = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[0]; //Change to .getDefaultScreenDevice(); when done
    private Player player;
    private GameState gameState;
    private Camera cam;

    private int UPS, FPS;

    public GamePanel() {
        mainJFrame = new JFrame("To be named");
        mainJFrame.setUndecorated(true);
        // fullscreen
        mainJFrame.setLocationRelativeTo(null);
        mainJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainJFrame.add(this);
        mainJFrame.setVisible(true);
        mainJFrame.getContentPane().setBackground(Color.BLACK);
        graphicsDevice.setFullScreenWindow(mainJFrame);
        //graphicsDevice.setDisplayMode(new DisplayMode(800,600, graphicsDevice.getDisplayMode().getBitDepth(), DisplayMode.REFRESH_RATE_UNKNOWN));
        setSize(mainJFrame.getWidth(), mainJFrame.getHeight());
        mainJFrame.pack();
        init();
    }

    public void init() {

        cam = new Camera(0, 0, getWidth(), getHeight());
        player = new Player();
        gameState = new GameState(getWidth(), getHeight(), player, cam, "save/save.txt");

        this.addKeyListener(new KeyInput(player, gameState));
        this.addMouseListener(new MouseInput(player, gameState));
        this.addMouseWheelListener(new MouseWheelInput(player, gameState));
        this.requestFocus();

        State.setState(gameState);
        if (State.getState() != null)
            State.getState().init();

        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = null;
        try {
            cursorImg = ImageIO.read(new File("resources/sprites/cursor.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        mainJFrame.getContentPane().setCursor(blankCursor);

        new Thread(this).start();
    }

    public void update() {
        if (State.getState() != null)
            State.getState().update();
        cam.update(player);
    }

    public void render() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }
        Graphics2D g2d = (Graphics2D) bs.getDrawGraphics();

        g2d.translate(-cam.getX(), -cam.getY());

        g2d.setColor(Color.BLACK);
        g2d.fillRect((int) cam.getX(), (int) cam.getY(), getWidth(), getHeight());

        if (State.getState() != null)
            State.getState().render(g2d);

        g2d.setColor(Color.GREEN);
        g2d.drawString("Updates: " + UPS + " FPS: " + FPS, (int) cam.getX() + getWidth() - 200, (int) cam.getY() + 20);

        g2d.translate(cam.getX(), cam.getY());

        g2d.dispose();
        bs.show();
    }

    public static JFrame getMainJFrame() {
        return mainJFrame;
    }

    public void run() {
        final int targetFPS = 60;
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double targetTime = 1000000000.0 / targetFPS;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (true) {
            render();
            long currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / targetTime;
            lastTime = currentTime;
            while (delta >= 1) {
                // the game will update targetFPS amount of times per second
                update();
                updates++;
                delta--;
            }
            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;

                System.out.println("Updates: " + updates + " FPS: " + frames);
                UPS = updates;
                FPS = frames;
                System.out.println("X: " + player.getX() + " Y: " + player.getY());
                updates = 0;
                frames = 0;
            }

            if (((lastTime - System.nanoTime()) + targetTime) / 1000000 > delta) {
                try {
                    Thread.sleep((int) (((lastTime - System.nanoTime()) + targetTime) / 1000000));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
