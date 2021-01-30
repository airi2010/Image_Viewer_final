
package apps.swing;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class BlockPanel extends JPanel implements ImageDisplay{
    
    private Image image;
    private BufferedImage bufferedImage;
    private BufferedImage bufferedImage2;
    private int x;
    private Shift shift;
    private Box box ;
    public BlockPanel() {
        MouseHandler mouseHandler = new MouseHandler();
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseHandler);
    }

    @Override
    public void paint(Graphics g){
        if(box !=null){
           g.setColor (Color.white);
            g.fillRect (box.x, box.y, box.width, box.height); 
        }
        box = new Box(bufferedImage);
        g.drawImage(bufferedImage, box.x, box.y, box.width, box.height, null);
        //if(bufferedImage!=null)g.drawImage(bufferedImage, box.x, box.y, box.width, box.height, null);
       // if(bufferedImage2!=null & x!=0)g.drawImage(bufferedImage2, x<0 ? box.x+x:x-box.x, 0, this);
    }
    private BufferedImage load(String name){
        try {
            return ImageIO.read(new File(name));
        } catch (IOException ex) {
            return null;
        }
    }
    
    @Override
    public void display(Image image){
        this.image = image;
        this.bufferedImage = load(image.getName());
        this.setBackground(Color.white);
        this.repaint();
    }
    
    @Override
    public Image current(){
        return image;
    }
    
    private Box boxOf(BufferedImage image){
        return new Box(image);
    }
    
    private class Box {
        int  x;
        int y;
        int width;
        int height;
        private final int px;
        private final int py;
        
        private Box(BufferedImage image) {
            this.px = getWidth();
            this.py = getHeight();
            this.width = calculateWidth(image.getWidth(), image.getHeight());
            this.height = calculateHeight(image.getWidth(), image.getHeight());
            this.x = (px-width)/2;
            this.y = (py-height)/2;
        }
        
        private int calculateWidth(double ix, double iy){
            double pr = (double) px / py;
            double ir = ix / iy;
            return ir > pr ? px : (int) (ix * py / iy);
        }
        
        private int calculateHeight(double ix, double iy){
            double pr = (double) px / py;
            double ir = ix / iy;
            return ir > pr ? (int) (iy * px / ix) : py;
        }
    }
    
    private Image imagear(int x) {
        if(x>0)return this.shift.left();
        if(x<0)return this.shift.right();
        return null;
    } 
    
    private static BufferedImage load(Image image){
        try {
            return ImageIO.read(new File(image.getName()));
        } catch (IOException ex) {
            return null;
        }
    }
    
     @Override
    public void on(Shift shift) {
        this.shift=shift;
    }

    private class MouseHandler implements MouseListener, MouseMotionListener {

        private int initial;

        MouseHandler() {
        }

        @Override
        public void mouseClicked(MouseEvent e) {
         
        }

        @Override
        public void mousePressed(MouseEvent e) {
            this.initial=e.getX();
        
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            int pos=e.getX();
            if(Math.abs(shift(pos))>getWidth()/2){
                image=imagear(shift(pos));
                bufferedImage=load(image);
            }
            x=0;
            repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        
        }

        @Override
        public void mouseExited(MouseEvent e) {
        
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            int x=shift(e.getX());
            if(x==0)bufferedImage2=null;
            else if(BlockPanel.this.x/x <=0)bufferedImage2=load(imagear(x));
            
            BlockPanel.this.x=x;
            repaint();
        
        }

        @Override
        public void mouseMoved(MouseEvent e) {
        
        }

        private int shift(int x) {
            return x-initial;
        }

    }
}
