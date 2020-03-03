import java.awt.*;
import java.util.Objects;

public class Fruit implements Renderable {

    int x, y;

    public Fruit(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.red);
        g.drawRect(this.x, this.y, Game.TILE_SIZE, Game.TILE_SIZE);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fruit fruit = (Fruit) o;
        return x == fruit.x &&
                y == fruit.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
