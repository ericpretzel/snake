import javax.swing.*;

public class Game implements Runnable {

    public static final int TILE_SIZE = 25;

    private Screen display;
    private Snake snake;
    boolean running;
    private Fruit fruit;


    public Game(Screen display) {
        this.display = display;
        this.snake = new Snake(TILE_SIZE * 13, TILE_SIZE * 13);
        this.running = false;

        this.display.addKeyListener(snake);
        this.display.add(snake);

        this.fruit = null;
    }

    public void run() {
        running = true;
        while (running) {
            SwingUtilities.invokeLater(() -> {
                display.repaint();
                display.revalidate();
            });

            snake.move();

            if (fruit == null) {
                do {
                    fruit = new Fruit((int) (Math.random() * (display.getWidth() - TILE_SIZE)), (int) (Math.random() * (display.getHeight() - TILE_SIZE)));
                } while (snake.eats(fruit));
                display.add(fruit);
            } else if (snake.eats(fruit)) {
                display.remove(fruit);
                this.fruit = null;
                snake.addNode();
            }

            if (snake.collided())
                running = false;

            try {
                Thread.sleep(350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
