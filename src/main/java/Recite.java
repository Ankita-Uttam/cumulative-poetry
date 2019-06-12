class Recite {

    public String recite (String[] storyLines, Reveal revealer) {
        String recitation = "";
        for (int i = Constants.FIRST_DAY; i <= storyLines.length; i++) {
            recitation += StringFormatter.addLineFeed(dayInformation(i), Constants.POS_END) +
                    revealer.revealForDayN(i, storyLines);
            recitation = StringFormatter.addLineFeed(recitation, Constants.POS_END);
        }
        return recitation;
    }

    private String dayInformation(int dayNumber) {
        return "Day " + dayNumber + " -";
    }
}
