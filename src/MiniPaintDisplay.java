import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MiniPaintDisplay extends JFrame {
    int currentTool = 1;

    public MiniPaintDisplay() {

        createMenuBar();
        createToolBar();

        setTitle("MiniPaint");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        var miniPaintPaintingArea = new MiniPaintPaintingArea();
        add(miniPaintPaintingArea);

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
    private JButton makeMyButton(String buttonName, final int buttonNumber) {
        JButton button = new JButton(buttonName);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    currentTool = buttonNumber;
            }
        });
        return button;
    }

    private void createToolBar() {
        var toolBar = new JToolBar(JToolBar.VERTICAL);
        var drawPointButton =  makeMyButton("Point", 1);
        var drawLineButton =  makeMyButton("Line", 2);
        var drawRectangleButton =  makeMyButton("Rectangle", 3);
        var drawTriangleButton =  makeMyButton("Triangle",4);
        var drawEllipseButton =  makeMyButton("Ellipse", 5);
        toolBar.add(drawPointButton);
        toolBar.add(drawLineButton);
        toolBar.add(drawRectangleButton);
        toolBar.add(drawTriangleButton);
        toolBar.add(drawEllipseButton);
        add(toolBar, BorderLayout.WEST);
    }


}
