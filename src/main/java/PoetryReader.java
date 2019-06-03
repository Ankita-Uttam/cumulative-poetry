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
            RevealingFormat reveal = RevealingFormat.getRevealingFormat(formatFlag);
            String filePath = Constants.RESOURCE_PATH + Constants.FILE_NAME;
            String[] story = null;

            switch (orderFlag) {
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
