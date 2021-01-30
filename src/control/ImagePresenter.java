
package control;


import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageDisplay.Shift;
import java.util.List;

public class ImagePresenter {
    private final List<Image>images;
    private final ImageDisplay imagedisplay;

    public ImagePresenter(List<Image> images, ImageDisplay imagedisplay) {
        this.images = images;
        this.imagedisplay = imagedisplay;
        this.imagedisplay.on(shift());
    }
    private int Current(){
        return images.indexOf(imagedisplay.current());
    }
    private ImageDisplay.Shift shift() {
        return new Shift(){
               @Override
             public Image left() {
                return images.get((Current()+1)% images.size());
             }

             @Override
             public Image right() {
                 return images.get((Current()-1+images.size())% images.size());
             }
        };
    }
    
}
