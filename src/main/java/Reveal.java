import java.io.*;
import java.util.*;

public class Reveal {

    public static void main(String args[]) {
        Reveal reveal = new Reveal();
        String output = null;
        switch (reveal.getParsedCommandMap(args).get("Action")) {
            case "Reveal":
                output = reveal.revealForDayN(Integer.parseInt(reveal.getParsedCommandMap(args).get("DayNumber")), reveal.getStory());
                break;
            case "Recite":
                output = reveal.recite(reveal.getStory());
                break;
        }

        System.out.println(output);
    }

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

    public Map<String, String> getParsedCommandMap(String[] arguments) {
        Map parsedMap = new HashMap<String, String>();
        if (arguments[0].equalsIgnoreCase("--reveal-for-day")) {
            parsedMap.put("Action", "Reveal");
            parsedMap.put("DayNumber", arguments[1]);
        } else {
            parsedMap.put("Action", "Recite");
        }
        return parsedMap;
    }

    public String revealForDayN(int dayNumber, String[] storyLines) {
        String revelation = "";
        if (dayNumber > 12) {
            dayNumber = 12;
        }
        if (dayNumber <= storyLines.length && dayNumber > 0) {
            revelation += "This is " + storyLines[storyLines.length - dayNumber];
            for (int i = storyLines.length - dayNumber + 1; i < storyLines.length; i++) {
                revelation += "\n\t" + storyLines[i];
            }
        } else {
            revelation += "No story for day 0";
        }
        return revelation;
    }

    public String recite (String[] storyLines) {
        String recitation = "";
        for (int i = 1; i <=12; i++) {
            recitation += "Day " + i + " -\n";
            recitation += revealForDayN(i, storyLines);
            recitation += "\n\n";
        }
        return  recitation;
    }
}
