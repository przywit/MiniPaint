import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

/**
 *
 */
public class MiniPaintPaintingArea extends JPanel {
    ObjectTransformation objectToTransform;
    MiniPaintDisplay display;
    Tool currentTool = new ObjectTransformation(this);
    Graphics2D g2d;

    public MiniPaintPaintingArea() {

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                currentTool.mousePressed(e);
        }

            @Override
            public void mouseReleased(MouseEvent e) {
                currentTool.mouseReleased(e);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    display.popupMenu.show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentTool.mouseDragged(e);
            }
        });
    }

    public void setDisplay(MiniPaintDisplay display){
        this.display = display;
    }
    public void setObjectToTransform(ObjectTransformation objectToTransform) {
        this.objectToTransform = objectToTransform;
    }

    public int getIndexOfObjectByMousePosition (int x, int y) {
        for (int i = shapesList.size() - 1; i >= 0; i--) {
            if (shapesList.get(i).contains(x,y)) {
                return i;
            }
        }
        return -1;
    }
    public void translateObject(int lengthOnXAxis, int lengthOnYAxis, int objectIndex) {
        AffineTransform transform = new AffineTransform();
        transform.translate(lengthOnXAxis,lengthOnYAxis);
        System.out.println(transform);

    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    ArrayList<Shape> shapesList = new ArrayList<>();
    ArrayList<Color> colorsList = new ArrayList<>();

    public void addColorToColorList () {
        Color color = new Color(display.currentColor.getRGB());
        colorsList.add(color);
    }

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
        for (int i = 0; i < shapesList.size(); i++) {
            g2d.setColor(colorsList.get(i));
            g2d.fill(shapesList.get(i));
        }
    }
}