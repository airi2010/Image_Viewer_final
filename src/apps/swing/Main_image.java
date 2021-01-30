package apps.swing;


import control.ImagePresenter;
import control.Command;
import control.NextImageCommand;
import control.PrevImageCommand;
import imageviewer.model.Image;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main_image extends JFrame {
    
    private List<Image> images;
    private BlockPanel imageDisplay;
    private final Map<String, Command> commands = new HashMap<>();
    private ImagePresenter imagePresenter;
    public Main_image(){
        this.setTitle("Image Viewer v2");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.getContentPane().add(imagePanel(), BorderLayout.CENTER);
        this.add(toolbar(), BorderLayout.SOUTH);
        execute();
    }
    
    public void execute(){
        this.images = new FileImageLoader(new File("imagenes")).load();
        this.imageDisplay.display(images.get(0));
        this.commands.put("<", new PrevImageCommand(images, imageDisplay));
        this.commands.put(">", new NextImageCommand(images, imageDisplay));
        this.imagePresenter = new ImagePresenter(images,imageDisplay);
        this.setVisible(true);
        setAlwaysOnTop(true);
        setAlwaysOnTop(false);
    }

    private JPanel imagePanel() {
        BlockPanel blockPanel = new BlockPanel();
        this.imageDisplay = blockPanel;
        return blockPanel;
    }

    private JPanel toolbar() {
        JPanel toolbar = new JPanel();
        toolbar.setBackground(Color.gray);
        toolbar.add(button("<"), BorderLayout.CENTER);
        toolbar.add(button(">"), BorderLayout.CENTER);
        //toolbar.add(button("Delete"), BorderLayout.WEST);
        return toolbar;
    }
    
    private JButton button(String name){
        JButton button = new JButton(name);
        button.addActionListener((ActionEvent e) -> {
            commands.get(name).execute();
        });
        return button;
    }
    
}
