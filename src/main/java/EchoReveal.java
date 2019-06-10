class EchoReveal extends RevealingFormat {

    protected String line(String line) {
        return repeatLines(line, 2);
    }

    private String repeatLines(String storyLine, int repeatFrequency) {
        StringBuilder repeatedLines = new StringBuilder();
        for (int i = 1; i <= repeatFrequency; i++) {
            repeatedLines.append(formattedStoryLine(storyLine));
        }
        return "" + repeatedLines;
    }
}
