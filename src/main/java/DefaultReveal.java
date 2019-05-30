class DefaultReveal extends RevealingFormat {

    protected String startPattern(String startLine) {
        return firstLine(startLine);
    }

    protected String remainingPattern(String storyLine) {
        return formattedStoryLine(storyLine);
    }
}
