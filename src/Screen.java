import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Screen extends JPanel {

    private List<Renderable> renderables;

    public Screen() {
        super(null);
        this.setPreferredSize(new Dimension(600, 600));
        this.setFocusable(true);
        this.setBackground(Color.white);

        renderables = new ArrayList<>();
    }

    public void add(Renderable renderable) {
        this.renderables.add(renderable);
    }

    public void remove(Renderable renderable) {
        this.renderables.remove(renderable);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Renderable renderable : renderables) {
            renderable.render(g);
        }
    }

}
