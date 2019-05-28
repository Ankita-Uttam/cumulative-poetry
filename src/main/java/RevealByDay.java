class RevealByDay {

    public String revealForDayN(String dayNumber, String[] storyLines) throws IllegalArgumentException {
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
        else if (dayNumber == Constants.DAY_ZERO)
            throw new IllegalArgumentException("wrong reveal command: illegal day number");

        return dayNumber;
    }

    String revealForDayN(int dayNumber, String[] storyLines) {
        String revelation = Constants.START_PHRASE + storyLines[storyLines.length - dayNumber];
        for (int i = storyLines.length - dayNumber + 1; i < storyLines.length; i++) {
            revelation += StringFormatter.addLineFeed( StringFormatter.addTab(storyLines[i], Constants.FORMAT_POS_START),
                    Constants.FORMAT_POS_START);
        }
        return revelation;
    }
}
