import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.util.ArrayList;

/**
 *Class is responsible for displaying every think on the screen.
 */
public class MiniPaintPaintingArea extends JPanel {
    ArrayList<Integer> centerXCordsList = new ArrayList<>();
    ArrayList<Integer> centerYCordsList = new ArrayList<>();
    ArrayList<Shape> shapesList = new ArrayList<>();
    ArrayList<Color> colorsList = new ArrayList<>();
    ObjectTransformation objectToTransform;
    MiniPaintDisplay display;
    Tool currentTool = new ObjectTransformation(this);
    AffineTransform transform;

    /**
     * in constructor, we create a interaction between user and painting area.
     */
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
                    display.getPopupMenu().show(e.getComponent(), e.getX(), e.getY());
                }
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                currentTool.mouseDragged(e);
            }
        });

        addMouseWheelListener(new MouseAdapter() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                currentTool.processMouseWheelRotation(e);
            }
        });
    }

    /**
     * method necessary to set a current display we have, we need a reference to that display to create pop up menu
     * @param display display
     */
    public void setDisplay(MiniPaintDisplay display){
        this.display = display;
    }

    /**
     * necessary to work with popUpMenu, we need reference to index of a selected object
     * @param objectToTransform well it is an objcet to transform
     */
    public void setObjectToTransform(ObjectTransformation objectToTransform) {
        this.objectToTransform = objectToTransform;
    }

    /**
     *  gives an index of object we selected with mouse (left-click)
     * @param x gets position of mouse on x Axis
     * @param y gets position of mouse on y Axis
     * @return returns object index of selected object
     */
    public int getIndexOfObjectByMousePosition (int x, int y) {
        for (int i = shapesList.size() - 1; i >= 0; i--) {
            if (shapesList.get(i).contains(x,y)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * method necessary to transform objects
     * any object is taken to 0,0 point  and then translated by position where he was + distance we made with dragged mouse
     * @param lengthOnXAxis information about distance made with dragged mouse on x Axis
     * @param lengthOnYAxis information about distance made with dragged mouse on y Axis
     * @param objectIndex object index
     */
    public void translateObject(int lengthOnXAxis, int lengthOnYAxis, int objectIndex) {
        int centerX = centerXCordsList.get(objectIndex);
        int centerY = centerYCordsList.get(objectIndex);
        transform = new AffineTransform();
        transform.translate(centerX + lengthOnXAxis, centerY + lengthOnYAxis);
        transform.translate(-centerX,-centerY);
        changeCenterXCordInCenterXCordsList(lengthOnXAxis, objectIndex);
        changeCenterYCordInCenterYCordsList(lengthOnYAxis, objectIndex);
        shapesList.set(objectIndex,transform.createTransformedShape(shapesList.get(objectIndex)));
    }

    /**
     * method necessary to scale objects
     * any object is taken to 0,0 point, scaled  and then translated by position where he was + distance we made with dragged mouse
     * @param deltaScale the scale that has changed by user mouse wheel input
     * @param objectIndex object index
     */
    public void scaleObject(float deltaScale, int objectIndex){
        int centerX = centerXCordsList.get(objectIndex);
        int centerY = centerYCordsList.get(objectIndex);
        transform = new AffineTransform();

        transform.translate(centerX, centerY);
        transform.scale(1 + deltaScale, 1 + deltaScale);
        transform.translate(-centerX,-centerY);

        shapesList.set(objectIndex,transform.createTransformedShape(shapesList.get(objectIndex)));
    }

    /**
     * method sets tool as active
     * @param currentTool ...
     */
    public void setCurrentTool(Tool currentTool) {
        this.currentTool = currentTool;
    }

    /**
     * necessary for translation, if we moved an object we need to remember what is has center cords now.
     * @param dx distance changed on x Axis
     * @param transformedObjectIndex object index
     */
    public void changeCenterXCordInCenterXCordsList(int dx, int transformedObjectIndex) {
        centerXCordsList.set(transformedObjectIndex, centerXCordsList.get(transformedObjectIndex) + dx);
    }

    /**
     * necessary for translation, if we moved an object we need to remember what is has center cords now.
     * @param dy distance changed on x Axis
     * @param transformedObjectIndex object index
     */
    public void changeCenterYCordInCenterYCordsList(int dy, int transformedObjectIndex) {
        centerYCordsList.set(transformedObjectIndex, centerYCordsList.get(transformedObjectIndex) + dy);
    }

    /**
     * method adds new color to colors list
     */
    public void addColorToColorList () {
        Color color = new Color(display.getCurrentColor().getRGB());
        colorsList.add(color);
    }

    /**
     * method adds new center cord on X axis to center X cords list
     * @param xCenterCord x Center Cord
     */
    public void addCenterXCordToCenterXCordsList (int xCenterCord) {
        centerXCordsList.add(xCenterCord);
    }

    /**
     * method adds new center cord on Y axis to center Y cords list
     * @param yCenterCord y Center cord
     */
    public void addCenterYCordToCenterYCordsList (int yCenterCord) {
        centerYCordsList.add(yCenterCord);
    }

    /**
     *
     * @param shape shape that has to be added to shapes list
     * @return generates an index of that shape ( necessary to build a triangle)
     */
    public int addShapeToShapesList(Shape shape) {
        shapesList.add(shape);
        return shapesList.size() - 1;
    }

    /**
     * method sets an shape on specified place in array list
     * @param shape shape that has to set
     * @param shapeIndex index of that shape
     */
    public void setShape(Shape shape, int shapeIndex) {
        if (shapeIndex < shapesList.size()){
            shapesList.set(shapeIndex, shape);
        }
    }

    /**
     * method responsible for creating "new option"
     */
    public void clearAllLists() {
        shapesList = new ArrayList<>();
        colorsList = new ArrayList<>();
        centerXCordsList = new ArrayList<>();
        centerYCordsList = new ArrayList<>();
        display.setCurrentColor(Color.black);
    }

    /**
     * method responsible for displaying graphics
     * @param g ...
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        for (int i = 0; i < shapesList.size(); i++) {
            g2d.setColor(colorsList.get(i));
            g2d.fill(shapesList.get(i));
        }
    }

    /**
     * method saves content of the arrays
     * @param file is file to save of arrays in
     */
    public void save(File file){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

            objectOutputStream.writeObject(shapesList);
            objectOutputStream.writeObject(colorsList);
            objectOutputStream.writeObject(centerXCordsList);
            objectOutputStream.writeObject(centerYCordsList);

            objectOutputStream.close();
            fileOutputStream.close();

        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }
    }
    /**
     * method loads content of the arrays
     * @param file is file to load of arrays to
     */
    public void load(File file){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            shapesList = (ArrayList<Shape>) objectInputStream.readObject();
            colorsList = (ArrayList<Color>) objectInputStream.readObject();
            centerXCordsList = (ArrayList<Integer>) objectInputStream.readObject();
            centerYCordsList = (ArrayList<Integer>) objectInputStream.readObject();

            objectInputStream.close();
            fileInputStream.close();

        }catch (IOException e){
            System.out.println(e.getLocalizedMessage());
        }catch (ClassNotFoundException e){
            System.out.println(e.getLocalizedMessage());
        }

    }
}