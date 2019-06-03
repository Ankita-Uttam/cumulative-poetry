import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DefaultStory {

    // Is it a utility function?
    static String[] getStory(String filePath) {
        String story = "";
        try {
            File file = new File(filePath);
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\\Z");
            story = sc.next();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return story.split("\n");
    }
}
