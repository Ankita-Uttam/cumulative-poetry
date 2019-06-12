import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class DefaultStory extends StoryOrder{

//    private DefaultStory() {
//        throw new IllegalStateException("Utility Class");
//    }

    // Is it a utility function?
    String[] getStory(String filePath) throws FileNotFoundException {
        return getFileContents(filePath);
    }
}
