import java.util.HashMap;
import java.util.Map;

class CommandParser {

    Map<String, String> getParsedCommandMap(String[] arguments) {
        Map<String, String> parsedMap = new HashMap<>();
        int startIndex = 0;

        parsedMap.put("Flag", "None");

        handleNullArguments(arguments.length);
        handleFlag(arguments, parsedMap);

        if (parsedMap.containsValue("Echo"))
            startIndex = 1;

        handleIllegalArguments(arguments, startIndex);

        boolean isReveal = arguments[startIndex].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER);

        if (isReveal) {
            parsedMap.put(Constants.KEY_ACTION, Constants.REVEAL);
            parsedMap.put(Constants.KEY_DAY_NUMBER, arguments[startIndex + 1]);
        } else {
            parsedMap.put(Constants.KEY_ACTION, Constants.RECITE);
        }
        return parsedMap;
    }

    private void handleIllegalArguments(String[] arguments, int startIndex) throws IllegalArgumentException {
        final int REVEAL_COMMAND_ARGUMENTS_LENGTH = startIndex + 2;
        final int RECITE_COMMAND_ARGUMENTS_LENGTH = startIndex + 1;

        boolean neitherRevealNorRecite = !(arguments[startIndex].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER)
                || arguments[startIndex].equalsIgnoreCase(Constants.RECITE_IDENTIFIER));
        boolean revealCommandWithUnnecessaryArguments = arguments.length > REVEAL_COMMAND_ARGUMENTS_LENGTH &&
                arguments[startIndex].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER);
        boolean reciteCommandWithUnnecessaryArguments = arguments.length > RECITE_COMMAND_ARGUMENTS_LENGTH &&
                arguments[startIndex].equalsIgnoreCase(Constants.RECITE_IDENTIFIER);

        if (neitherRevealNorRecite)
            throw new IllegalArgumentException("PoetryReader: " + arguments[startIndex] + ": command not found");

        if (revealCommandWithUnnecessaryArguments)
            throw new IllegalArgumentException("wrong reveal command: unnecessary arguments");

        if (reciteCommandWithUnnecessaryArguments)
            throw new IllegalArgumentException("wrong recite command: unnecessary arguments");
    }

    private void handleNullArguments(int argumentsCount) {
        if (argumentsCount == 0)
            throw new IllegalArgumentException("expected arguments: no arguments received");
    }

    private void handleFlag(String[] arguments, Map<String, String> parsedMap) {
        if (arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.ECHO_FLAG))
            parsedMap.replace("Flag", "Echo");
    }
}
