class EchoReveal extends RevealingFormat {

    String revealForDayN(int dayNumber, String[] storyLines) {
        String revelation = Constants.START_PHRASE + storyLines[storyLines.length - dayNumber] +
                repeatLines(storyLines[storyLines.length - dayNumber], 1);
        for (int i = storyLines.length - dayNumber + 1; i < storyLines.length; i++) {
            revelation += repeatLines(storyLines[i], 2);
        }
        return revelation;
    }

    private String repeatLines(String storyLine, int repeatFrequency) {
        String repeatedLines = "";
        for (int i = 1; i <= repeatFrequency; i++) {
            repeatedLines += formattedStoryLine(storyLine);
        }
        return repeatedLines;
    }
}
