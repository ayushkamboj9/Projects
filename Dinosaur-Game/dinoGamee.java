import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class dinoGamee extends JPanel implements KeyListener, ActionListener {
    private int width = 800; // Width of the game window
    private int height = 400; // Height of the game window
    private int dinoX = 100; // Initial X position of the dinosaur
    private int dinoY = height - 150; // Initial Y position of the dinosaur
    private int dinoHeight = 50; // Height of the dinosaur
    private boolean jumping = false; // Flag to track if the dinosaur is jumping
    private int jumpHeight = 300; // Height of a single jump
    private int jumpSpeed = 5; // Speed of the dinosaur's jump
    private ArrayList

    <Obstacle> obstacles; // List to store obstacles
    private int obstacleWidth = 20; // Width of an obstacle
    private int obstacleHeight = 30; // Height of an obstacle
    private Timer timer; // Timer for game updates
    private int score = 0; // Current score
    private int obstacleGap = 10; // Gap between obstacles and bottom of the screen
    private BufferedImage dinosaurImage; // Image of the dinosaur

    public dinoGamee() {
        JFrame frame = new JFrame("Dino Game");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        frame.addKeyListener(this);
        frame.setVisible(true);

        // Load the dinosaur image
        try {
            dinosaurImage = ImageIO.read(new File("dinosaur.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        obstacles = new ArrayList<>();
        timer = new Timer(20, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.setColor(Color.BLACK);

        // Draw the dinosaur image
        if (dinosaurImage != null) {
            g.drawImage(dinosaurImage, dinoX, dinoY, null);
        }

        // Draw obstacles
        for (Obstacle obs : obstacles) {
            g.fillRect(obs.getX(), obs.getY(), obstacleWidth, obstacleHeight);
        }

        // Draw score
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 10, 20);
    }

    public void actionPerformed(ActionEvent e) {
        score++; // Increment the score

        // Move the dinosaur
        if (jumping) {
            if (dinoY > height - 50 - jumpHeight) {
                dinoY -= jumpSpeed; // Move the dinosaur upwards during jump
            } else {
                jumping = false; // End the jump when reaching the maximum height
            }
        } else {
            if (dinoY < height - 50) {
                dinoY += jumpSpeed; // Move the dinosaur downwards after jump
            }
        }

        // Move obstacles and check for collisions
        for (Obstacle obs : obstacles) {
            obs.move(); // Move each obstacle towards the left
            if (obs.getX() < -obstacleWidth) {
                obstacles.remove(obs); // Remove obstacles that are out of bounds
                break;
            }
            if (obs.intersects(dinoX, dinoY, 20, dinoHeight)) {
                timer.stop(); // Stop the game timer when collision occurs
                JOptionPane.showMessageDialog(this, "Kaam Khatam! Apka score:  " + score);
                System.exit(0); // Exit the game
            }
        }

        // Add new obstacle every 100 score points
        if (score % 100 == 0) {
            Random rand = new Random();
            int gap = rand.nextInt(100) + 50;
            obstacles.add(
                    new Obstacle(width, height - obstacleGap - obstacleHeight - gap, obstacleWidth, obstacleHeight));
        }

        repaint(); // Redraw the game screen
    }

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_SPACE && !jumping) {
            jumping = true; // Set jumping flag when spacebar is pressed
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        new dinoGamee();
    }
}

class Obstacle {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed = 5; // Speed at which the obstacle moves towards the left

    public Obstacle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void move() {
        x -= speed; // Move the obstacle towards the left
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean intersects(int dinoX, int dinoY, int dinoWidth, int dinoHeight) {
        Rectangle obstacleRect = new Rectangle(x, y, width, height);
        Rectangle dinoRect = new Rectangle(dinoX, dinoY, dinoWidth, dinoHeight);
        return obstacleRect.intersects(dinoRect); // Check for collision between obstacle and dinosaur
    }
}
