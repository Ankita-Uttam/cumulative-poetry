import jdk.internal.loader.Resource;

import java.io.FileNotFoundException;
import java.util.Map;

public class PoetryReader {

    public static void main(String[] args) {
        CommandParser parser = new CommandParser();
        String output = "";

        try {
            Map<String, String> parsedMap = parser.getParsedCommandMap(args);
            String formatFlag = parsedMap.get(Constants.KEY_FLAG_FORMAT);
            String orderFlag = parsedMap.get(Constants.KEY_FLAG_ORDER);
            String seed = parsedMap.get(Constants.KEY_SEED);
            Reveal reveal = new Reveal(formatFlag);
            String[] story = null;

            switch (orderFlag) {
                case "Random":
                    RandomStory randomStory = new RandomStory();
                    output += randomStory.seedInfo(seed);
                    story = randomStory.getStory(getFilePath(Constants.FILE_NAME), seed);
                    break;
                case "None":
                    story = DefaultStory.getStory(getFilePath(Constants.FILE_NAME));
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
        } catch(IllegalArgumentException | FileNotFoundException ex) {
            output = ex.getMessage();
        }

        System.out.println(output);
    }

    private static String getFilePath(String relativePath) {
//        return PoetryReader.class.getClassLoader().getResource(relativePath).toExternalForm();
        return relativePath;
    }
}
