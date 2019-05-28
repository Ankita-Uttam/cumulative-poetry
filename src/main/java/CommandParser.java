import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    public Map<String, String> getParsedCommandMap(String[] arguments) {
        CommandParser parser = new CommandParser();
        Map parsedMap = new HashMap<String, String>();

        parser.handleIllegalArguments(arguments);

        boolean isReveal = arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER);

        if (isReveal) {
            parsedMap.put(Constants.KEY_ACTION, Constants.REVEAL);
            parsedMap.put(Constants.KEY_DAY_NUMBER, arguments[1]);
        } else {
            parsedMap.put(Constants.KEY_ACTION, Constants.RECITE);
        }
        return parsedMap;
    }

    private void handleIllegalArguments(String[] arguments) throws IllegalArgumentException {
        final int REVEAL_COMMAND_ARGUMENTS_LENGTH = 2;
        final int RECITE_COMMAND_ARGUMENTS_LENGTH = 1;
        boolean noArguments = (arguments.length == 0);

        if (noArguments)
            throw new IllegalArgumentException("expected arguments: no arguments received");

        boolean neitherRevealNorRecite = !(arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER)
                || arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.RECITE_IDENTIFIER));
        boolean revealCommandWithUnnecessaryArguments = arguments.length > REVEAL_COMMAND_ARGUMENTS_LENGTH &&
                arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER);
        boolean reciteCommandWithUnnecessaryArguments = arguments.length > RECITE_COMMAND_ARGUMENTS_LENGTH &&
                arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.RECITE_IDENTIFIER);

        if (neitherRevealNorRecite)
            throw new IllegalArgumentException("PoetryReader: " + arguments[Constants.START_INDEX] + ": command not found");
        else if (revealCommandWithUnnecessaryArguments)
            throw new IllegalArgumentException("wrong reveal command: unnecessary arguments");
        else if (reciteCommandWithUnnecessaryArguments)
            throw new IllegalArgumentException("wrong recite command: unnecessary arguments");
    }
}
