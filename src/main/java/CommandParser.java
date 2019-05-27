import java.util.HashMap;
import java.util.Map;

public class CommandParser {

    public Map<String, String> getParsedCommandMap(String[] arguments) {
        Map parsedMap = new HashMap<String, String>();
        if (arguments[0].equalsIgnoreCase("--reveal-for-day")) {
            parsedMap.put("Action", "Reveal");
            parsedMap.put("DayNumber", arguments[1]);
        } else {
            parsedMap.put("Action", "Recite");
        }
        return parsedMap;
    }
}
