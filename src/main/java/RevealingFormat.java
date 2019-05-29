public abstract class RevealingFormat {

    String revealForDayN(String dayNumber, String[] storyLines) throws IllegalArgumentException {
        int _dayNumber = handleDayNumber(dayNumber, storyLines.length);
        return revealForDayN(_dayNumber, storyLines);
    }

    private int handleDayNumber(String day, int linesCount) throws IllegalArgumentException{
        int dayNumber;
        try {
            dayNumber = Integer.parseInt(day);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException("wrong reveal command: illegal day number");
        }

        if (dayNumber >  linesCount)
            dayNumber = linesCount;

        if (dayNumber == Constants.DAY_ZERO)
            throw new IllegalArgumentException("wrong reveal command: illegal day number");

        return dayNumber;
    }

    String formattedStoryLine(String storyLine) {
        String formattedLine = StringFormatter.addTab(storyLine, Constants.FORMAT_POS_START);
        return StringFormatter.addLineFeed(formattedLine, Constants.FORMAT_POS_START);
    }

    abstract String revealForDayN(int dayNumber, String[] storyLines);
}
