import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PoetryReader {

    public static void main(String args[]) {
        CommandParser parser = new CommandParser();
        PoetryReader reader = new PoetryReader();
        Reveal reveal = new Reveal();
        String output = null;
        try {
            switch (parser.getParsedCommandMap(args).get(Constants.KEY_ACTION)) {
                case "Reveal":
                    output = reveal.revealForDayN(parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER),
                            reader.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME));
                    break;
                case "Recite":
                    output = reveal.recite(reader.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME));
                    break;
            }
        } catch(IllegalArgumentException ex) {
            output = ex.getMessage();
        }

        System.out.println(output);
    }

    // Is it a utility function?
    public String[] getStory(String filePath) {
        String[] story = new String[Constants.MAX_LINES];
        try {
            FileReader fileReader = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fileReader);
            String storyLine;
            int lineIndex = Constants.START_INDEX;
            while ((storyLine = br.readLine()) != null) {
                story[lineIndex++] = storyLine;
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return story;
    }
}
