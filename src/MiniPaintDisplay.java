import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

/**
 * Class is responsible for displaying every think on the screen.
 * Class has all necessary components such as tool-bar , menu-bar and also popupMenu.
 */
public class MiniPaintDisplay extends JFrame {
    MiniPaintPaintingArea minipaintPaintingArea;
    JPopupMenu popupMenu;
    Color currentColor = Color.black;

    /**
     *  Constructor creates painting area and creates all necessary containers
     */
    public MiniPaintDisplay() {
        minipaintPaintingArea = new MiniPaintPaintingArea();
        minipaintPaintingArea.setDisplay(this);

        createMenuBar();
        createToolBar();
        cretePopupMenu();

        setTitle("MiniPaint");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add(minipaintPaintingArea);

    }

    /**
     * method creates menu-bar.
     * clicking on help menu-item we give user information -> how to use that program.
     * clicking on info menu-item we give user information -> destination,autor and name of our program.
     * we do that by creating new frame, which is displayed after a click.
     */
    private void createMenuBar() {

        var menuBar = new JMenuBar();

        var fileMenu = new JMenu("File");
        var infoMenu = new JMenu("Info");
        var helpMenu = new JMenu("Help");

        var helpMenuItem = new JMenuItem("Help");

        helpMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Help");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setSize(500,200);
                frame.setResizable(false);
                JLabel lHelp1 = new JLabel("Choose paint tool from tool-bar and start drawing by dragging mouse");
                JLabel lHelp2 = new JLabel("To transform shape choose ObjTrans, and select and object by left click");
                JLabel lHelp3 = new JLabel("To deselect : left click on empty space in painting area");
                JLabel lHelp4 = new JLabel("To scale selected shape use mouse wheal, to move shape click and drag.");
                lHelp1.setBounds(0,10, 450,20);
                lHelp2.setBounds(0,40,450,20);
                lHelp3.setBounds(0,70,450,20);
                lHelp3.setBounds(0,100,450,20);
                frame.add(lHelp1);
                frame.add(lHelp2);
                frame.add(lHelp3);
                frame.add(lHelp4);

                frame.setVisible(true);
            }
        });

        var infoMenuItem = new JMenuItem("Info");

        infoMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame();
                frame.setTitle("Info");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setSize(400,200);
                frame.setResizable(false);
                JLabel name = new JLabel("Name: Mini Paint");
                JLabel destination = new JLabel("Destination: Made to let user spend time with figures");
                JLabel author =  new JLabel("Author: Witold Przybył");
                name.setBounds(0,10, 100,20);
                destination.setBounds(0,40,300,20);
                author.setBounds(0,60,100,20);
                frame.add(name);
                frame.add(destination);
                frame.add(author);

                frame.setVisible(true);
            }
        });

        var newMenuItem = new JMenuItem("New");
        var openMenuItem = new JMenuItem("Open");
        openMenuItem.addActionListener(e -> load()); ////// Is ther any difference by useing "lambda" ? /////////
        openMenuItem.addActionListener(event -> load());
        var saveMenuItem = new JMenuItem("Save");
        saveMenuItem.addActionListener(event -> save());

        newMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               minipaintPaintingArea.clearAllLists();
               minipaintPaintingArea.repaint();
            }
        });
        helpMenu.add(helpMenuItem);
        infoMenu.add(infoMenuItem);
        fileMenu.add(newMenuItem);
        fileMenu.add(openMenuItem);
        fileMenu.add(saveMenuItem);
        menuBar.add(fileMenu);
        menuBar.add(infoMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    /**
     * method creates tool-bar.
     * clicking on the tool will change tool status to an active tool, which is necessary for drawing stuff on the board.
     */
    private void createToolBar() {
        var toolBar = new JToolBar(JToolBar.VERTICAL);
        var objectTransformationButton = new JButton("ObjTrans");
        var drawRectangleButton = new JButton("Rectangle");
        var drawTriangleButton = new JButton("Triangle");
        var drawEllipseButton = new JButton("Ellipse");
        toolBar.add(objectTransformationButton);
        toolBar.add(drawRectangleButton);
        toolBar.add(drawTriangleButton);
        toolBar.add(drawEllipseButton);
        add(toolBar, BorderLayout.WEST);


        objectTransformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minipaintPaintingArea.setCurrentTool(new ObjectTransformation(minipaintPaintingArea));
            }
        });
        drawRectangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minipaintPaintingArea.setCurrentTool(new DrawRectangle(minipaintPaintingArea));

            }
        });
        drawTriangleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minipaintPaintingArea.setCurrentTool(new DrawTriangle(minipaintPaintingArea));

            }
        });
        drawEllipseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                minipaintPaintingArea.setCurrentTool(new DrawEllipse(minipaintPaintingArea));

            }
        });
    }

    /**
     * methor creates popupMenu
     * in this method we also want to change color of selected object.
     * if index of Object to transform is -1 it means it is not selected.
     *in current color variable we store info about current color we are already painting
     * if user has selected an object and changed/(canceled) color we want to check if that object has the color user wanted.
     *
     */
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
                if (minipaintPaintingArea.objectToTransform.indexOfObjectToTransform != -1) {
                    if(currentColor != minipaintPaintingArea.colorsList.get(minipaintPaintingArea.objectToTransform.indexOfObjectToTransform)) {
                        minipaintPaintingArea.colorsList.set(minipaintPaintingArea.objectToTransform.indexOfObjectToTransform, currentColor);
                    }
                }
                minipaintPaintingArea.repaint();
            }
        });
        popupMenu.add(colorChooser);
        minipaintPaintingArea.setComponentPopupMenu(popupMenu);
    }

    /**
     *
     */
    private void save(){
        var savePanel = new JFileChooser();
        int success = savePanel.showSaveDialog(this);

        if(success == JFileChooser.APPROVE_OPTION){
            File file = savePanel.getSelectedFile();
            minipaintPaintingArea.save(file);
        }
    }
    /**
     *
     */
    private void load(){
        var openPanel = new JFileChooser();
        int success = openPanel.showOpenDialog(this);

        if(success == JFileChooser.APPROVE_OPTION){
            File file = openPanel.getSelectedFile();
            minipaintPaintingArea.load(file);
            minipaintPaintingArea.repaint();
        }
    }

}
