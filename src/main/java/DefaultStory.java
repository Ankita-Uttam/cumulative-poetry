import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DefaultStory {

    private DefaultStory() {
        throw new IllegalStateException("Utility Class");
    }

    // Is it a utility function?
    static String[] getStory(String filePath) throws FileNotFoundException {
        String story = "";
        File file = new File(filePath);
        try(Scanner sc = new Scanner(file)) {
            sc.useDelimiter("\\Z");
            story = sc.next();
        }
        return story.split("\n");
    }
}
