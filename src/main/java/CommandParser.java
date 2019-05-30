import java.util.HashMap;
import java.util.Map;

class CommandParser {

    Map<String, String> getParsedCommandMap(String[] arguments) {
        Map<String, String> parsedMap = new HashMap<>();
        int startIndex = 0;

        parsedMap.put(Constants.KEY_FLAG, Constants.FLAG_NONE);

        handleNullArguments(arguments.length);
        handleFlag(arguments, parsedMap);

        if (parsedMap.containsValue(Constants.FLAG_ECHO))
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
        final String ERR_REVEAL = "wrong reveal command: unnecessary arguments";
        final String ERR_RECITE = "wrong recite command: unnecessary arguments";
        final int REVEAL_COMMAND_ARGUMENTS_LENGTH = startIndex + 2;
        final int RECITE_COMMAND_ARGUMENTS_LENGTH = startIndex + 1;

        boolean revealOrRecite = (arguments[startIndex].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER)
                || arguments[startIndex].equalsIgnoreCase(Constants.RECITE_IDENTIFIER));
        boolean revealWithUnnecessaryArguments = arguments.length > REVEAL_COMMAND_ARGUMENTS_LENGTH &&
                arguments[startIndex].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER);
        boolean reciteWithUnnecessaryArguments = arguments.length > RECITE_COMMAND_ARGUMENTS_LENGTH &&
                arguments[startIndex].equalsIgnoreCase(Constants.RECITE_IDENTIFIER);

        if (!revealOrRecite)
            throw new IllegalArgumentException("PoetryReader: " + arguments[startIndex] + ": command not found");

        if (revealWithUnnecessaryArguments)
            throw new IllegalArgumentException(ERR_REVEAL);

        if (reciteWithUnnecessaryArguments)
            throw new IllegalArgumentException(ERR_RECITE);
    }

    private void handleNullArguments(int argumentsCount) {
        final String ERR_NULL_ARGS = "expected arguments: no arguments received";
        if (argumentsCount == 0)
            throw new IllegalArgumentException(ERR_NULL_ARGS);
    }

    private void handleFlag(String[] arguments, Map<String, String> parsedMap) {
        if (arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.ECHO_IDENTIFIER))
            parsedMap.replace(Constants.KEY_FLAG, Constants.FLAG_ECHO);
    }
}
