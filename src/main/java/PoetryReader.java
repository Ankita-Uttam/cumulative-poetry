import java.io.*;
import java.util.Scanner;

public class PoetryReader {

    public static void main(String[] args) {
        CommandParser parser = new CommandParser();
        PoetryReader reader = new PoetryReader();

        String output = null;
        try {
            RevealingFormat reveal = reader.getRevealingFormat(parser.getParsedCommandMap(args).get("Flag"));

            switch (parser.getParsedCommandMap(args).get(Constants.KEY_ACTION)) {
                case "Reveal":
                    output = reveal.revealForDayN(parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER),
                            reader.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME));
                    break;
                case "Recite":
                    output = new Recite().recite(reader.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME), reveal);
                    break;
            }
        } catch(IllegalArgumentException ex) {
            output = ex.getMessage();
        }

        System.out.println(output);
    }

    private RevealingFormat getRevealingFormat(String flag) {
        RevealingFormat reveal = null;
        switch (flag) {
            case "Echo":
                reveal = new EchoReveal();
                break;
            case "None":
                reveal = new DefaultReveal();
        }
        return reveal;
    }

    // Is it a utility function?
    String[] getStory(String filePath) {
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
