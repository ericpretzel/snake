import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame fr = new JFrame();
        Screen sc = new Screen();
        fr.add(sc);
        fr.pack();
        fr.setLocationRelativeTo(null);
        fr.setVisible(true);

        new Thread(new Game(sc)).start();
    }
}
