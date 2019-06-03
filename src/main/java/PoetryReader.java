import java.io.*;
import java.util.*;

public class PoetryReader {

    public static void main(String[] args) {
        CommandParser parser = new CommandParser();

        String output = "";
        try {
            Map<String, String> parsedMap = parser.getParsedCommandMap(args);
            String format_flag = parsedMap.get(Constants.KEY_FLAG_FORMAT);
            String order_flag = parsedMap.get(Constants.KEY_FLAG_ORDER);
            String seed = parsedMap.get(Constants.KEY_SEED);
            RevealingFormat reveal = RevealingFormat.getRevealingFormat(format_flag);
            String filePath = Constants.RESOURCE_PATH + Constants.FILE_NAME;
            String[] story = null;

            switch (order_flag) {
                case "Random":
                    RandomStory randomStory = new RandomStory();
                    output += randomStory.seedInfo(seed);
                    story = randomStory.getStory(filePath, seed);
                    break;
                case "None":
                    story = DefaultStory.getStory(filePath);
            }

            switch (parsedMap.get(Constants.KEY_ACTION)) {
                case "Reveal":
                    String dayNumber = parsedMap.get(Constants.KEY_DAY_NUMBER);
                    output += reveal.revealForDayN(dayNumber, story);
                    break;
                case "Recite":
                    output += new Recite().recite(story, reveal);
                    break;
            }
        } catch(IllegalArgumentException ex) {
            output = ex.getMessage();
        }

        System.out.println(output);
    }
}
