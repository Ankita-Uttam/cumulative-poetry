import com.sun.org.apache.bcel.internal.Const;

import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    public Map<String, String> getParsedCommandMap(String[] arguments) {
        CommandParser parser = new CommandParser();
        Map parsedMap = new HashMap<String, String>();

        parser.handleIllegalArguments(arguments);

        if (arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER)) {
            parsedMap.put(Constants.KEY_ACTION, Constants.REVEAL);
            parsedMap.put(Constants.KEY_DAY_NUMBER, arguments[1]);
        } else {
            parsedMap.put(Constants.KEY_ACTION, Constants.RECITE);
        }
        return parsedMap;
    }

    private void handleIllegalArguments(String[] arguments) throws IllegalArgumentException {
        if (arguments.length == 0)
            throw new IllegalArgumentException("expected arguments: no arguments received");
        else if (!(arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER) || arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.RECITE_IDENTIFIER)))
            throw new IllegalArgumentException("PoetryReader: " + arguments[Constants.START_INDEX] + ": command not found");
        else if (arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.REVEAL_IDENTIFIER) && arguments.length > 2)
            throw new IllegalArgumentException("wrong reveal command: unnecessary arguments");
        else if (arguments[Constants.START_INDEX].equalsIgnoreCase(Constants.RECITE_IDENTIFIER) && arguments.length > 1)
            throw new IllegalArgumentException("wrong recite command: unnecessary arguments");
    }
}
