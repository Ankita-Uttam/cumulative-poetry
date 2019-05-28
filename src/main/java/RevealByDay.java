public class RevealByDay extends RevealingFormat {

    public String reveal(String[]storyLines, String ...args) {
        return revealForDayN(args[0], storyLines);
    }

    private String revealForDayN(String dayNumber, String[] storyLines) throws IllegalArgumentException {
        int _dayNumber = handleDayNumber(dayNumber);
        return revealForDayN(_dayNumber, storyLines);
    }

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
