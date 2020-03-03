import org.w3c.dom.Node;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Snake extends KeyAdapter {

    private Node head, tail;
    private int size;

    public Snake(int x, int y) {
        this.tail = this.head = new Node(x, y);
        this.size = 1;
    }

    public void move() {
        head.move(head.dX, head.dY);
    }

    public void addNode() {
        this.tail.next = new Node(tail.x - tail.dX, tail.y - tail.dY);
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
                if (head.dX != 1) head.dX = -1;
                head.dY = 0;
                break;
            case KeyEvent.VK_RIGHT:
                if (head.dX != -1) head.dX = 1;
                head.dY = 0;
                break;
            case KeyEvent.VK_DOWN:
                head.dX = 0;
                if (head.dY != -1) head.dY = 1;
                break;
            case KeyEvent.VK_UP:
                head.dX = 0;
                if (head.dY != 1) head.dY = -1;
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

        void move(int dX, int dY) {
            int prevDX = this.dX;
            int prevDY = this.dY;

            this.dX = dX;
            this.dY = dY;

            x += this.dX;
            y += this.dY;

            if (next != null)
                next.move(prevDX, prevDY);
        }

        boolean intersects(Node node) {
            return node != null && this.x == node.x && this.y == node.y;
        }
    }
}
