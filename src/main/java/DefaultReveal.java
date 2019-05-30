class DefaultReveal extends RevealingFormat {

    String revealForDayN(int dayNumber, String[] storyLines) {
        int startIndex = storyLines.length - dayNumber;
        String revelation = Constants.START_PHRASE + storyLines[startIndex];
        for (int i = startIndex + 1; i < storyLines.length; i++) {
            revelation += formattedStoryLine(storyLines[i]);
        }
        return revelation;
    }
}
