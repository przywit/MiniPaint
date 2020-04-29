import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.*;
import java.util.ArrayList;

/**
 * elo mordo
 */
public class MiniPaintPaintingArea extends JPanel {

    public MiniPaintPaintingArea() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                repaint();
            }
        });
    }

    ArrayList<Shape> shapesList = new ArrayList<Shape>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : shapesList) {
            g2d.draw(shape);
        }
    }
}