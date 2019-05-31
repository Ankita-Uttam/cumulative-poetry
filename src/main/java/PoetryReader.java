import java.io.*;
import java.util.*;

public class PoetryReader {

    public static void main(String[] args) {
        CommandParser parser = new CommandParser();

        String output = null;
        try {
            Map<String, String> parsedMap = parser.getParsedCommandMap(args);
            String flag = parsedMap.get(Constants.KEY_FLAG);
            RevealingFormat reveal = RevealingFormat.getRevealingFormat(flag);
            String filePath = Constants.RESOURCE_PATH + Constants.FILE_NAME;

            switch (parser.getParsedCommandMap(args).get(Constants.KEY_ACTION)) {
                case "Reveal":
                    String dayNumber = parsedMap.get(Constants.KEY_DAY_NUMBER);
                    output = reveal.revealForDayN(dayNumber, getStory(filePath));
                    break;
                case "Recite":
                    output = new Recite().recite(getStory(filePath), reveal);
                    break;
            }
        } catch(IllegalArgumentException ex) {
            output = ex.getMessage();
        }

        System.out.println(output);
    }

    // Is it a utility function?
    public static String[] getStory(String filePath) {
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
