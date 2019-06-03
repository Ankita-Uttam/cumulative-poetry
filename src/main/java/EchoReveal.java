class EchoReveal extends RevealingFormat {

    protected String startPattern(String startLine) {

        return firstLine(startLine) + repeatLines(startLine, 1);
    }

    protected String remainingPattern(String storyLine) {

        return repeatLines(storyLine, 2);
    }

    private String repeatLines(String storyLine, int repeatFrequency) {
        String repeatedLines = "";
        for (int i = 1; i <= repeatFrequency; i++) {
            repeatedLines += formattedStoryLine(storyLine);
        }
        return repeatedLines;
    }
}
