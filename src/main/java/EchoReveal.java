class EchoReveal extends RevealingFormat {

    String revealForDayN(int dayNumber, String[] storyLines) {
        String revelation = Constants.START_PHRASE + storyLines[storyLines.length - dayNumber] +
                StringFormatter.addLineFeed( StringFormatter.addTab(storyLines[storyLines.length - dayNumber], Constants.FORMAT_POS_START),
                        Constants.FORMAT_POS_START);
        for (int i = storyLines.length - dayNumber + 1; i < storyLines.length; i++) {
            for (int j = 0; j < 2; j++) {
                revelation += StringFormatter.addLineFeed( StringFormatter.addTab(storyLines[i], Constants.FORMAT_POS_START),
                        Constants.FORMAT_POS_START);
            }
        }
        return revelation;
    }
}
