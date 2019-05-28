public class Reveal {

    // reveal
    public String revealForDayN(String dayNumber, String[] storyLines) throws IllegalArgumentException {
        int _dayNumber = handleDayNumber(dayNumber);
        return revealForDayN(_dayNumber, storyLines);
    }

    // recite
    public String recite (String[] storyLines) {
        String recitation = "";
        for (int i = Constants.FIRST_DAY; i <= Constants.LAST_DAY; i++) {
            recitation += StringFormatter.addLineFeed(getDayInformation(i), Constants.FORMAT_POS_END) +
            revealForDayN(i, storyLines);
            recitation = StringFormatter.addLineFeed(recitation, Constants.FORMAT_POS_END);
            recitation = StringFormatter.addLineFeed(recitation, Constants.FORMAT_POS_END);
        }
        return recitation;
    }

    // recite
    private String getDayInformation(int dayNumber) {
        return "Day " + dayNumber + " -";
    }

    // common
    private String revealForDayN(int dayNumber, String[] storyLines) {
        String revelation = Constants.START_PHRASE + storyLines[storyLines.length - dayNumber];
        for (int i = storyLines.length - dayNumber + 1; i < storyLines.length; i++) {
            revelation += StringFormatter.addLineFeed( StringFormatter.addTab(storyLines[i], Constants.FORMAT_POS_START),
                    Constants.FORMAT_POS_START);
        }
        return revelation;
    }

    // reveal
    private int handleDayNumber(String day) throws IllegalArgumentException{
        int dayNumber;
        try {
            dayNumber = Integer.parseInt(day);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("wrong reveal command: illegal day number");
        }

        if (dayNumber > Constants.LAST_DAY )
            dayNumber = Constants.LAST_DAY;
        else if (dayNumber == Constants.DAY_ZERO)
            throw new IllegalArgumentException("wrong reveal command: illegal day number");

        return dayNumber;
    }
}
