public class Recite extends RevealingFormat{

    public String reveal(String[]storyLines, String ...args) {
        return recite(storyLines);
    }

    private String recite (String[] storyLines) {
        String recitation = "";
        for (int i = Constants.FIRST_DAY; i <= Constants.LAST_DAY; i++) {
            recitation += StringFormatter.addLineFeed(getDayInformation(i), Constants.FORMAT_POS_END) +
                    revealForDayN(i, storyLines);
            recitation = StringFormatter.addLineFeed(recitation, Constants.FORMAT_POS_END);
            recitation = StringFormatter.addLineFeed(recitation, Constants.FORMAT_POS_END);
        }
        return recitation;
    }

    private String getDayInformation(int dayNumber) {
        return "Day " + dayNumber + " -";
    }
}
