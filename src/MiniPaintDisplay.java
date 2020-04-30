import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;


public class MiniPaintDisplay extends JFrame {

    MiniPaintPaintingArea paintPaintingArea;

    public MiniPaintDisplay() {

        paintPaintingArea = new MiniPaintPaintingArea();

        createMenuBar();
        createToolBar();

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
        var drawPointButton = new JButton("Point");
        var drawLineButton = new JButton("Line");
        var drawRectangleButton = new JButton("Rectangle");
        var drawTriangleButton = new JButton("Triangle");
        var drawEllipseButton = new JButton("Ellipse");
        toolBar.add(drawPointButton);
        toolBar.add(drawLineButton);
        toolBar.add(drawRectangleButton);
        toolBar.add(drawTriangleButton);
        toolBar.add(drawEllipseButton);
        add(toolBar, BorderLayout.WEST);



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


}
