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
    AffineTransform transform;
    int scale = 1;

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
    public void transformObject (int lengthOnXAxis,int lengthOnYAxis,int objectIndex) {
        int centerX = centerXCordsList.get(objectIndex);
        int centerY = centerYCordsList.get(objectIndex);
        transform = new AffineTransform();
            transform.translate(-centerX,-centerY);
            transform.scale(scale,scale);
            transform.translate(centerX + lengthOnXAxis, centerY + lengthOnYAxis);
        changeCenterXCordInCenterXCordsList(lengthOnXAxis, objectIndex);
        changeCenterYCordInCenterYCordsList(lengthOnYAxis, objectIndex);
        for (int i = 0; i < shapesList.size() - 1; i++) {
            if (objectIndex == i) {
                shapesList.set(objectIndex,transform.createTransformedShape(shapesList.get(i)));
            }
        }
    }

    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    ArrayList<Integer> centerXCordsList = new ArrayList<>();
    ArrayList<Integer> centerYCordsList = new ArrayList<>();
    ArrayList<Shape> shapesList = new ArrayList<>();
    ArrayList<Color> colorsList = new ArrayList<>();

    public void changeCenterXCordInCenterXCordsList(int dx, int transformedObjectIndex) {
        centerXCordsList.set(transformedObjectIndex, centerXCordsList.get(transformedObjectIndex) + dx);
}
    public void changeCenterYCordInCenterYCordsList(int dy, int transformedObjectIndex) {
        centerYCordsList.set(transformedObjectIndex, centerYCordsList.get(transformedObjectIndex) + dy);
    }

    public void addColorToColorList () {
        Color color = new Color(display.currentColor.getRGB());
        colorsList.add(color);
    }
    public void addCenterXCordToCenterXCordsList (int xCenterCord) {
        centerXCordsList.add(xCenterCord);
    }
    public void addCenterYCordToCenterYCordsList (int yCenterCord) {
        centerYCordsList.add(yCenterCord);
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

    public void clearAllLists() {
        shapesList = new ArrayList<>();
        colorsList = new ArrayList<>();
        centerXCordsList = new ArrayList<>();
        centerYCordsList = new ArrayList<>();
        display.currentColor = Color.black;
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