public abstract class RevealingFormat {

    public static RevealingFormat getRevealingFormat(String flag) {
        switch (flag) {
            case "Echo":
                return new EchoReveal();
            case "None":
                return new DefaultReveal();
        }
        return null;
    }

    protected abstract String line(String line);


    String formattedStoryLine(String storyLine) {
        String formattedLine = StringFormatter.addLineFeed(storyLine, Constants.POS_END);
        return StringFormatter.addTab(formattedLine, Constants.POS_END);
    }

    String firstLine(String startLine) {
        return Constants.START_PHRASE + startLine;
    }

//    private String remainingLines(int index, String[] storyLines) {
//        String remainingLines = "";
//        for (int i = index + 1; i < storyLines.length; i++) {
//            remainingLines += remainingPattern(storyLines[i]);
//        }
//        return remainingLines;
//    }

    // TODO - what's pattern? - Is it necessary to invest words? I'll avoid if I can.

//    protected abstract String remainingPattern(String storyLine);
//
//    protected abstract String startPattern(String startLine);
}
