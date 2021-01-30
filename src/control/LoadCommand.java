
package control;

import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.util.List;

public class LoadCommand implements Command {
    
    private final ImageLoader imageLoader;
    private final List<Image> imageList;
    private final ImageDisplay imageDisplay;

    public LoadCommand(ImageLoader imageLoader, List<Image> imageList, ImageDisplay imageDisplay) {
        this.imageLoader = imageLoader;
        this.imageList = imageList;
        this.imageDisplay = imageDisplay;
    }

    @Override
    public void execute() {
        this.imageList.clear();
        this.imageList.addAll(imageLoader.load());
        imageDisplay.display(imageList.get(0));
    }

}
