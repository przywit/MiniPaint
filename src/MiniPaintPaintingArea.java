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

    Tool currentTool = new DrawPoint(this);

    public MiniPaintPaintingArea() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentTool.mousePressed(e);
        }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentTool.mouseReleased(e);
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentTool.mouseDragged(e);
            }
        });
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    ArrayList<Shape> shapesList = new ArrayList<>();

    public void addShapeToShapesList(Shape shape) {
        shapesList.add(shape);
    }

    public void clearShapesList() {
        shapesList = new ArrayList<>();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (Shape shape : shapesList) {
            g2d.draw(shape);
            g2d.fill(shape);
        }
    }
}