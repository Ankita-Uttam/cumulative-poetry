import java.util.HashMap;
import java.util.Map;

class CommandParser {

    private static final int MIN_ARGUMENTS_REQUIRED = 1;

    Map<String, String> getParsedCommandMap(String[] arguments) {
        Map<String, String> parsedMap = new HashMap<>();

        parsedMap.put(Constants.KEY_FLAG_FORMAT, Constants.FLAG_NONE);
        parsedMap.put(Constants.KEY_FLAG_ORDER, Constants.FLAG_NONE);

        handleIllegalCommands(arguments);
        handleCorrectCommands(arguments, parsedMap);

        return parsedMap;
    }

    private void parseRemainArguments(int argsCount, Map<String, String> parsedMap, String[] args) {
        switch (argsCount) {
            case 2:
                if (args[0].equalsIgnoreCase(Constants.ECHO_IDENTIFIER))
                    parsedMap.replace(Constants.KEY_FLAG_FORMAT, Constants.FLAG_ECHO);
                else {
                    parsedMap.replace(Constants.KEY_FLAG_ORDER, Constants.FLAG_RANDOM);
                    parsedMap.put(Constants.KEY_SEED, Constants.DEFAULT_SEED);
                }
                break;
            case 3:
                parsedMap.replace(Constants.KEY_FLAG_FORMAT, Constants.FLAG_ECHO);
                parsedMap.replace(Constants.KEY_FLAG_ORDER, Constants.FLAG_RANDOM);
                parsedMap.put(Constants.KEY_SEED, Constants.DEFAULT_SEED);
                break;
            case 4:
                parsedMap.replace(Constants.KEY_FLAG_ORDER, Constants.FLAG_RANDOM);
                parsedMap.put(Constants.KEY_SEED, args[args.length - 1]);
                break;
            case 5:
                parsedMap.replace(Constants.KEY_FLAG_FORMAT, Constants.FLAG_ECHO);
                parsedMap.replace(Constants.KEY_FLAG_ORDER, Constants.FLAG_RANDOM);
                parsedMap.put(Constants.KEY_SEED, args[args.length - 1]);
                break;
        }
    }

    private void handleCorrectCommands(String[] args, Map<String, String> parsedMap) {
        if (args.length == MIN_ARGUMENTS_REQUIRED) {
            parsedMap.put(Constants.KEY_ACTION, Constants.RECITE);
        } else {
            if ((args[0] + args[1]).contains(Constants.RECITE_IDENTIFIER)) {
                parsedMap.put(Constants.KEY_ACTION, Constants.RECITE);
                parseRemainArguments(args.length, parsedMap, args);
            } else {
                parsedMap.put(Constants.KEY_ACTION, Constants.REVEAL);
                if (isEcho(args[0])) parsedMap.put(Constants.KEY_DAY_NUMBER, args[2]);
                else parsedMap.put(Constants.KEY_DAY_NUMBER, args[1]);
                parseRemainArguments(args.length - 1, parsedMap, args);
            }
        }
    }

    private void handleIllegalCommands(String[] args) {

        handleEdgeCases(args.length);
        handleSyntaxCases(args);
    }

    private void handleEdgeCases(int argsCount) {
        if (argsCount < 1) {
            throw new IllegalArgumentException(Constants.NO_ARGS);
        } else if (argsCount > 6) {
            throw new IllegalArgumentException(Constants.EXTRA_ARGS);
        }
    }

    private void handleSyntaxCases(String[] args) {
        try {
            int formatIndex = 0;
            if (isEcho(args[0])) formatIndex = 1;

            handleFormat(args[formatIndex]);
            handleOptions(args, getOptionsIndex(args, formatIndex));
        } catch (IndexOutOfBoundsException ex) {
            throw new IllegalArgumentException(Constants.INSUFFICIENT_ARGS);
        }
    }

    private void handleFormat(String arg) {
        if (!(isRecite(arg) || isReveal(arg)))
            throw new IllegalArgumentException(Constants.CMD_NOT_FOUND);
    }

    private int getOptionsIndex(String[] args, int formatIndex) {
        int optionsIndex = 0;
        if (isRecite(args[formatIndex])) optionsIndex = formatIndex + 1;
        else if (isReveal(args[formatIndex])) {
            if (args.length <= formatIndex + 1) {
                throw new IllegalArgumentException(Constants.INSUFFICIENT_ARGS);
            }
            optionsIndex = formatIndex + 2;
        }
        return optionsIndex;
    }

    private void handleOptions(String[] args, int index) {
        int argIndex = index;
        while (args.length > argIndex) {
            switch (argIndex - index) {
                case 0:
                    if (!isRandom(args[argIndex]))
                        throw new IllegalArgumentException(Constants.CMD_NOT_FOUND);
                    break;
                case 1:
                    if (!isSeed(args[argIndex]))
                        throw new IllegalArgumentException(Constants.CMD_NOT_FOUND);
                    else if (isSeed(args[argIndex])) {
                        if (args.length <= argIndex + 1) {
                            throw new IllegalArgumentException(Constants.INSUFFICIENT_ARGS);
                        }
                    }
                    break;
            }
            argIndex++;
        }
    }

    private boolean isEcho(String arg) {
        return arg.equalsIgnoreCase(Constants.ECHO_IDENTIFIER);
    }

    private boolean isReveal(String arg) {
        return arg.equalsIgnoreCase(Constants.REVEAL_IDENTIFIER);
    }

    private boolean isRecite(String arg) {
        return arg.equalsIgnoreCase(Constants.RECITE_IDENTIFIER);
    }

    private boolean isRandom(String arg) {
        return arg.equalsIgnoreCase(Constants.RANDOM_IDENTIFIER);
    }

    private boolean isSeed(String arg) {
        return arg.equalsIgnoreCase(Constants.SEED_IDENTIFIER);
    }
}
