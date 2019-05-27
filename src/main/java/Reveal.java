public class Reveal {

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
