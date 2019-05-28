public class Reveal {

    public String revealForDayN(String dayNumber, String[] storyLines) throws IllegalArgumentException {
        int _dayNumber = handleDayNumber(dayNumber);
        return revealForDayN(_dayNumber, storyLines);
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

    private String revealForDayN(int dayNumber, String[] storyLines) {
        String revelation = "";
        revelation += "This is " + storyLines[storyLines.length - dayNumber];
        for (int i = storyLines.length - dayNumber + 1; i < storyLines.length; i++) {
            revelation += "\n\t" + storyLines[i];
        }
        return revelation;
    }

    private int handleDayNumber(String day) throws IllegalArgumentException{
        int dayNumber = 0;
        try {
            dayNumber = Integer.parseInt(day);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("wrong reveal command: illegal day number");
        }

        if (dayNumber > 12 )
            dayNumber = 12;
        else if (dayNumber == 0)
            throw new IllegalArgumentException("wrong reveal command: illegal day number");

        return dayNumber;
    }
}
