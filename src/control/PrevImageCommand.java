
package control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import java.util.List;

public class PrevImageCommand implements Command {
    
    private final  List<Image> imageList;
    private final ImageDisplay imageDisplay;

    public PrevImageCommand(List<Image> imageList, ImageDisplay imageDisplay) {
        this.imageList = imageList;
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        imageDisplay.display(prev());
    }
    
    private Image prev () {
        int index = imageList.indexOf(imageDisplay.current());
        index = (index - 1 + imageList.size()) % imageList.size();
        return imageList.get(index);
    }

}
