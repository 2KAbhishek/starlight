import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Starlight extends Canvas implements ActionListener {
    public int n, size;
    static Data[] stars;

    public void init(int n) {
        this.n = n;
    }

    // Draw a circle of radius r at (x,y)
    public void drawCircle(Graphics g, int x, int y, int r) {
        int d = 2 * r;
        g.fillOval(x - r, y - r, d, d);
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (int i = 0; i < n; i++) {
            g.setColor(stars[i].color);
            drawCircle(g, (int) stars[i].x, (int) stars[i].y, (int) stars[i].size);
        }
    }

    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < n; i++) {
            stars[i].move();
        }
        repaint();
        Toolkit.getDefaultToolkit().sync();
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Stars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        int n = Integer.parseInt(args[0]);

        stars = new Data[n];
        for (int i = 0; i < n; i++) {
            stars[i] = new Data();
        }

        Starlight nbody = new Starlight();
        nbody.setBackground(Color.BLACK);
        nbody.size = 800;
        nbody.setPreferredSize(new Dimension(nbody.size, nbody.size));
        nbody.init(n);
        frame.add(nbody);
        frame.pack();

        Timer timer = new Timer(100, nbody);
        timer.start();

        frame.setVisible(true);
    }
}

class Data {
    int x, y, size, windowSize = 800;
    Color color;
    double velocity, dt = 0.1, maxVel = 10, maxMass = 10;

    Data() {
        x = x + (int) (Math.random() * windowSize);
        y = y + (int) (Math.random() * windowSize);
        color = new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        size = (int) (Math.random() * maxMass) + 1;
        velocity = Math.random() * maxVel + 1;
    }

    public void move() {
        x += (int) (Math.sin(y / 100 + (Math.random() * dt)) * velocity);
        y += (int) (Math.sin(x / 100 + Math.random() * dt) * velocity);
    }
}
