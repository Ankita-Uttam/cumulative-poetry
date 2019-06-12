import jdk.internal.loader.Resource;

import java.io.FileNotFoundException;

public class PoetryReader {

    public static void main(String[] args) {
        CommandParser parser = new CommandParser(args);
        String output = "";

        try {
            String formatFlag = parser.getFormatFlag();
            String orderFlag = parser.getOrderFlag();
            Integer seed = parser.getSeedValue();
            Reveal reveal = new Reveal(formatFlag);
            StoryOrder order = StoryOrder.getStoryOrder(orderFlag, seed);
            String[] story = order.getStory(getFilePath(Constants.FILE_NAME));

            System.out.println(parser.getActionFlag());
            switch (parser.getActionFlag()) {
                case "Reveal":
                    int dayNumber = parser.getDayNumber();
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
//        System.out.println(PoetryReader.class.getResource("/poetry.txt").getFile());

//        return PoetryReader.class.getClassLoader().getResource(relativePath).toExternalForm();
        return relativePath;
    }
}
