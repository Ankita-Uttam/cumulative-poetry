public abstract class RevealingFormat {

    String revealForDayN(String dayNumber, String[] storyLines) throws IllegalArgumentException {
        int _dayNumber = handleDayNumber(dayNumber, storyLines.length);
        int startIndex = storyLines.length - _dayNumber;
        String revelation = startPattern(storyLines[startIndex]);
        return revelation + remainingLines(startIndex, storyLines);
    }

    private int handleDayNumber(String day, int linesCount) throws IllegalArgumentException{
        int dayNumber;
        final String message = "wrong reveal command: illegal day number";
        try {
            dayNumber = Integer.parseInt(day);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(message);
        }

        if (dayNumber >  linesCount)
            dayNumber = linesCount;

        if (dayNumber == Constants.DAY_ZERO) {
            throw new IllegalArgumentException(message);
        }

        return dayNumber;
    }

    String formattedStoryLine(String storyLine) {
        String formattedLine = StringFormatter.addTab(storyLine, Constants.POS_START);
        return StringFormatter.addLineFeed(formattedLine, Constants.POS_START);
    }

    String firstLine(String startLine) {
        return Constants.START_PHRASE + startLine;
    }

    private String remainingLines(int index, String[] storyLines) {
        String remainingLines = "";
        for (int i = index + 1; i < storyLines.length; i++) {
            remainingLines += remainingPattern(storyLines[i]);
        }
        return remainingLines;
    }

    protected abstract String remainingPattern(String storyLine);

    protected abstract String startPattern(String startLine);
}
