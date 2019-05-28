import java.io.*;
import java.util.Scanner;

public class PoetryReader {

    public static void main(String[] args) {
        CommandParser parser = new CommandParser();
        PoetryReader reader = new PoetryReader();
        String output = null;
        try {
            switch (parser.getParsedCommandMap(args).get(Constants.KEY_ACTION)) {
                case "Reveal":
                    output = new RevealByDay().revealForDayN(parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER),
                            reader.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME));
                    break;
                case "Recite":
                    output = new Recite().recite(reader.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME), new RevealByDay());
                    break;
            }
        } catch(IllegalArgumentException ex) {
            output = ex.getMessage();
        }

        System.out.println(output);
    }

    // Is it a utility function?
    public String[] getStory(String filePath) {
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
