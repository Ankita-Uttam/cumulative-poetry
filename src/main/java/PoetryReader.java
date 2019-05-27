import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class PoetryReader {

    public static void main(String args[]) {
        CommandParser parser = new CommandParser();
        PoetryReader reader = new PoetryReader();
        ReadingFormat reveal = new ReadingFormat();
        String output = null;
        switch (parser.getParsedCommandMap(args).get("Action")) {
            case "Reveal":
                output = reveal.revealForDayN(Integer.parseInt(parser.getParsedCommandMap(args).get("DayNumber")), reader.getStory());
                break;
            case "Recite":
                output = reveal.recite(reader.getStory());
                break;
        }

        System.out.println(output);
    }

    // Is it a utility function?
    public String[] getStory() {
        String[] story = new String[12];
        try {
            FileReader fileReader = new FileReader("/Users/ankita.uttam/practice/Java/cumulative-poetry/src/main/resources/poetry.txt");
            BufferedReader br = new BufferedReader(fileReader);
            String storyLine;
            int lineIndex = 0;
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
