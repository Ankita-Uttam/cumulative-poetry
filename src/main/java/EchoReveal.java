class EchoReveal extends RevealingFormat {

    String revealForDayN(int dayNumber, String[] storyLines) {
        int startIndex = storyLines.length - dayNumber;
        String revelation = Constants.START_PHRASE + storyLines[startIndex] +
                repeatLines(storyLines[startIndex], 1);
        for (int i = startIndex + 1; i < storyLines.length; i++) {
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
