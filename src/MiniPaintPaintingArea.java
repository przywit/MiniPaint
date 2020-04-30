import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * elo mordo
 */
public class MiniPaintPaintingArea extends JPanel {

    Tool currentTool = new ObjectTransformation(this);

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

    public int addShapeToShapesList(Shape shape) {
        shapesList.add(shape);
        return shapesList.size() - 1;
    }
    public void setShape(Shape shape, int shapeIndex) {
        if (shapeIndex < shapesList.size()){
            shapesList.set(shapeIndex, shape);
        }
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