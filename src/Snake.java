import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake extends KeyAdapter implements Renderable {

    private Node head, tail;
    private int size;

    public Snake(int x, int y) {
        this.tail = this.head = new Node(x, y);
        this.size = 1;
    }

    final Color[] rainbow = new Color[] {
            Color.red,
            Color.orange,
            Color.yellow,
            Color.green,
            Color.blue,
            Color.magenta
    };
    public void render(Graphics g) {
        Node point = this.head;
        int index = 0;
        while (point != null) {
            g.setColor(rainbow[index]);
            g.fillRect(point.x, point.y, Game.TILE_SIZE, Game.TILE_SIZE);
            index = (index + 1) % rainbow.length;
            point = point.next;
        }
    }

    public boolean eats(Fruit fruit) {
        Node point = head;
        while (point != null) {
            if(
                    head.x + Game.TILE_SIZE >= fruit.x &&
                    head.y + Game.TILE_SIZE >= fruit.y &&
                    head.y <= fruit.y + Game.TILE_SIZE &&
                    head.x <= fruit.x + Game.TILE_SIZE
            )
                return true;
            point = point.next;
        }
        return false;
    }

    public void move() {
        head.move(head.x + head.dX, head.y + head.dY);
    }

    public void addNode() {
        this.tail.next = new Node(tail.x - tail.dX * Game.TILE_SIZE, tail.y - tail.dY * Game.TILE_SIZE);
        this.tail = this.tail.next;
        size++;
    }

    public boolean collided() {
        Node point = head.next;
        while (point != null) {
            if (head.intersects(point))
                return true;
            point = point.next;
        }
        return false;
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (head.dX != Game.TILE_SIZE) head.dX = -Game.TILE_SIZE;
                head.dY = 0;
                break;
            case KeyEvent.VK_RIGHT:
                if (head.dX != -Game.TILE_SIZE) head.dX = Game.TILE_SIZE;
                head.dY = 0;
                break;
            case KeyEvent.VK_DOWN:
                head.dX = 0;
                if (head.dY != -Game.TILE_SIZE) head.dY = Game.TILE_SIZE;
                break;
            case KeyEvent.VK_UP:
                head.dX = 0;
                if (head.dY != Game.TILE_SIZE) head.dY = -Game.TILE_SIZE;
                break;
        }
    }

    private static class Node {
        Node next;
        int x, y;
        int dX, dY;

        Node(int x, int y) {
            this.next = null;
            this.x = x;
            this.y = y;
            dX = dY = 0;
        }

        void move(int x, int y) {
            int prevX = this.x;
            int prevY = this.y;

            this.dX = x - this.x;
            this.dY = y - this.y;

            this.x = x;
            this.y = y;

            if (next != null)
                next.move(prevX, prevY);
        }

        boolean intersects(Node node) {
            return node != null && this.x == node.x && this.y == node.y;
        }
    }
}
