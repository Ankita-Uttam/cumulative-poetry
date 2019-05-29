class DefaultReveal extends RevealingFormat {

    String revealForDayN(int dayNumber, String[] storyLines) {
        String revelation = Constants.START_PHRASE + storyLines[storyLines.length - dayNumber];
        for (int i = storyLines.length - dayNumber + 1; i < storyLines.length; i++) {
            revelation += StringFormatter.addLineFeed( StringFormatter.addTab(storyLines[i], Constants.FORMAT_POS_START),
                    Constants.FORMAT_POS_START);
        }
        return revelation;
    }
}
