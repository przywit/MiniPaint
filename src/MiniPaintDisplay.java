import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class MiniPaintDisplay extends JFrame {
    MiniPaintPaintingArea paintPaintingArea;
    JPopupMenu popupMenu;
    Color currentColor = Color.black;

    public MiniPaintDisplay() {
        paintPaintingArea = new MiniPaintPaintingArea();
        paintPaintingArea.setDisplay(this);

        createMenuBar();
        createToolBar();
        cretePopupMenu();

        setTitle("MiniPaint");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(paintPaintingArea);

    }

    private void createMenuBar() {

        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        var infoMenu = new JMenu("Info");
        var helpMenu = new JMenu("Help");

        var newMenuItem = new JMenuItem("New");
        var openMenuItem = new JMenuItem("Open");
        var saveMenuItem = new JMenuItem("Save");

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               paintPaintingArea.clearShapesList();
               paintPaintingArea.repaint();
            }
        });

        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(infoMenu);
        menuBar.add(helpMenu);

        setJMenuBar(menuBar);
    }


    private void createToolBar() {
        var toolBar = new JToolBar(JToolBar.VERTICAL);
        var objectTransformationButton = new JButton("ObjTrans");
        var drawPointButton = new JButton("Point");
        var drawLineButton = new JButton("Line");
        var drawRectangleButton = new JButton("Rectangle");
        var drawTriangleButton = new JButton("Triangle");
        var drawEllipseButton = new JButton("Ellipse");
        toolBar.add(objectTransformationButton);
        toolBar.add(drawPointButton);
        toolBar.add(drawLineButton);
        toolBar.add(drawRectangleButton);
        toolBar.add(drawTriangleButton);
        toolBar.add(drawEllipseButton);
        add(toolBar, BorderLayout.WEST);


        objectTransformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPaintingArea.setCurrentTool(new ObjectTransformation(paintPaintingArea));
            }
        });

        drawPointButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPaintingArea.setCurrentTool(new DrawPoint(paintPaintingArea));
            }
        });
        drawLineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPaintingArea.setCurrentTool(new DrawLine(paintPaintingArea));

            }
        });
        drawRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPaintingArea.setCurrentTool(new DrawRectangle(paintPaintingArea));

            }
        });
        drawTriangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPaintingArea.setCurrentTool(new DrawTriangle(paintPaintingArea));

            }
        });
        drawEllipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                paintPaintingArea.setCurrentTool(new DrawEllipse(paintPaintingArea));

            }
        });
    }

    private void cretePopupMenu() {
        popupMenu = new JPopupMenu();
        var colorChooser = new JMenuItem("Choose color");
        colorChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null,"Choose Color", null);
                if (color != null) {
                    currentColor = color;
                }
            }
        });
        popupMenu.add(colorChooser);
        paintPaintingArea.setComponentPopupMenu(popupMenu);
    }

}
