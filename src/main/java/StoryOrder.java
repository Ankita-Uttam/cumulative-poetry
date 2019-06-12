import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class StoryOrder {

    public static StoryOrder getStoryOrder(String flag, Integer seedValue) {
        StoryOrder order = null;
        switch (flag){
            case "Random":
                order = new RandomStory(seedValue);
                break;
            case "None":
                order = new DefaultStory();
                break;
        }
        return order;
    }

    static String[] getFileContents(String filepath) throws FileNotFoundException {
        String story = "";
        File file = new File(filepath);
        try(Scanner sc = new Scanner(file)) {
            sc.useDelimiter("\\Z");
            story = sc.next();
        }
        return story.split("\n");
    }

    abstract String[] getStory(String filepath) throws FileNotFoundException;
}