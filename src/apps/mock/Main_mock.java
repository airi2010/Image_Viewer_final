package apps.mock;

import control.Command;
import control.ExitCommand;
import control.LoadCommand;
import control.NextImageCommand;
import control.NullCommand;
import control.PrevImageCommand;
import imageviewer.model.Image;
import imageviewer.view.ImageDisplay;
import imageviewer.view.ImageLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main_mock {

    public void start() {
        Scanner scanner = new Scanner(System.in);
        Map<String, Command> commands = initCommands(createImageDisplay());
        commands.get("l").execute();
        while (true) {
            commands.getOrDefault(scanner.next(), new NullCommand()).execute();
        }
    }
    
    private static Map<String, Command> initCommands(ImageDisplay imageDisplay) {
        List<Image> imageList = new ArrayList<>();
        ImageLoader imageLoader = new MockImageLoader();
        Map<String, Command> commands = new HashMap<>();
        commands.put("l", new LoadCommand(imageLoader, imageList, imageDisplay));
        commands.put("n", new NextImageCommand(imageList, imageDisplay));
        commands.put("p", new PrevImageCommand(imageList, imageDisplay));
        commands.put("q", new ExitCommand());
        return commands;
    }
    
    private static ImageDisplay createImageDisplay() {
        ImageDisplay imageDisplay = new MockImageDisplay();
        return imageDisplay;
    }

}
