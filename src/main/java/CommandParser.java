import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    public Map<String, String> getParsedCommandMap(String[] arguments) {
        CommandParser parser = new CommandParser();
        Map parsedMap = new HashMap<String, String>();

        parser.handleIllegalArguments(arguments);

        if (arguments[0].equalsIgnoreCase("--reveal-for-day")) {
            parsedMap.put("Action", "Reveal");
            parsedMap.put("DayNumber", arguments[1]);
        } else {
            parsedMap.put("Action", "Recite");
        }
        return parsedMap;
    }

    private void handleIllegalArguments(String[] arguments) throws IllegalArgumentException {
        if (arguments.length == 0)
            throw new IllegalArgumentException("expected arguments: no arguments received");
        else if (!(arguments[0].equalsIgnoreCase("--reveal-for-day") || arguments[0].equalsIgnoreCase("--recite")))
            throw new IllegalArgumentException("PoetryReader: " + arguments[0] + ": command not found");
        else if (arguments[0].equalsIgnoreCase("--reveal-for-day") && arguments.length > 2)
            throw new IllegalArgumentException("wrong reveal command: unnecessary arguments");
        else if (arguments[0].equalsIgnoreCase("--recite") && arguments.length > 1)
            throw new IllegalArgumentException("wrong recite command: unnecessary arguments");
    }
}
