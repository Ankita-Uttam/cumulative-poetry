class Reveal {

    private RevealingFormat revealingFormat;

    Reveal (String flag) {
        revealingFormat = RevealingFormat.getRevealingFormat(flag);
    }

    String revealForDayN(String dayNumber, String[] storyLines) {
        int _dayNumber = handleDayNumber(dayNumber, storyLines.length);
        int startIndex = storyLines.length - _dayNumber;
        String revelation = Constants.START_PHRASE;
        for (int i = startIndex; i < storyLines.length; i++) {
            revelation += revealingFormat.line(storyLines[i]);
        }
        return revelation;
    }

    private int handleDayNumber(String day, int linesCount) {
        int dayNumber = Integer.parseInt(day);

        if (dayNumber >  linesCount)
            dayNumber = linesCount;

        if (dayNumber == Constants.DAY_ZERO) {
            throw new IllegalArgumentException(Constants.ILLEGAL_ARG_TYPE);
        }

        return dayNumber;
    }
}
