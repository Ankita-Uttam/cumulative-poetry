public class Recite {

    public String recite (String[] storyLines) {
        String recitation = "";
        for (int i = Constants.FIRST_DAY; i <= Constants.LAST_DAY; i++) {
            recitation += StringFormatter.addLineFeed(getDayInformation(i), Constants.FORMAT_POS_END) +
                    new RevealByDay().revealForDayN(i, storyLines);
            recitation = StringFormatter.addLineFeed(recitation, Constants.FORMAT_POS_END);
            recitation = StringFormatter.addLineFeed(recitation, Constants.FORMAT_POS_END);
        }
        return recitation;
    }

    private String getDayInformation(int dayNumber) {
        return "Day " + dayNumber + " -";
    }
}
