class CommandParser {

    private static final int MIN_ARGUMENTS_REQUIRED = 1;
    private String formatFlag;
    private String orderFlag;
    private String actionFlag;
    private Integer dayNumber;
    private Integer seedValue;

    public String getFormatFlag() {
        return formatFlag;
    }

    private void setFormatFlag(String formatFlag) {
        this.formatFlag = formatFlag;
    }

    public String getOrderFlag() {
        return orderFlag;
    }

    private void setOrderFlag(String orderFlag) {
        this.orderFlag = orderFlag;
    }

    public String getActionFlag() {
        return actionFlag;
    }

    private void setActionFlag(String actionFlag) {
        this.actionFlag = actionFlag;
    }

    public Integer getDayNumber() {
        return dayNumber;
    }

    private void setDayNumber(Integer dayNumber) {
        this.dayNumber = dayNumber;
    }

    public Integer getSeedValue() {
        return seedValue;
    }

    private void setSeedValue(Integer seedValue) {
        this.seedValue = seedValue;
    }

    CommandParser (String[] arguments) {
        try {
            setFormatFlag(Constants.FLAG_NONE);
            setOrderFlag(Constants.FLAG_NONE);

            handleIllegalCommands(arguments);
            handleCorrectCommands(arguments);
        } catch(NumberFormatException ex) {
            throw new IllegalArgumentException(Constants.WRONG_ARG_TYPE);
        }
    }

    private void parseRemainArguments(int argsCount, String[] args) {
        switch (argsCount) {
            case 2:
                if (args[0].equalsIgnoreCase(Constants.ECHO_IDENTIFIER)) {
                    setFormatFlag(Constants.FLAG_ECHO);
                }
                else {
                    setOrderFlag(Constants.FLAG_RANDOM);
                    setSeedValue(Constants.DEFAULT_SEED);
                }
                break;
            case 3:
                setFormatFlag(Constants.FLAG_ECHO);
                setOrderFlag(Constants.FLAG_RANDOM);
                setSeedValue(Constants.DEFAULT_SEED);
                break;
            case 4:
                int seedIndex = args.length - 1;
                setOrderFlag(Constants.FLAG_RANDOM);
                setSeedValue(handleNumberArgument(args[seedIndex]));
                break;
            case 5:
                int _seedIndex = args.length - 1;
                setFormatFlag(Constants.FLAG_ECHO);
                setOrderFlag(Constants.FLAG_RANDOM);
                setSeedValue(handleNumberArgument(args[_seedIndex]));
                break;
        }
    }

    private void handleCorrectCommands(String[] args) {
        if (args.length == MIN_ARGUMENTS_REQUIRED) {
            setActionFlag(Constants.RECITE);
        } else {
            if ((args[0] + args[1]).contains(Constants.RECITE_IDENTIFIER)) {
                setActionFlag(Constants.RECITE);
                parseRemainArguments(args.length, args);
            } else {
                setActionFlag(Constants.REVEAL);
                if (isEcho(args[0]))
                    setDayNumber(handleNumberArgument(args[2]));
                else
                    setDayNumber(handleNumberArgument(args[1]));
                parseRemainArguments(args.length - 1, args);
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
            handleNumberArgument(args[formatIndex + 1]);
            optionsIndex = formatIndex + 2;
        }
        return optionsIndex;
    }

    private Integer handleNumberArgument(String arg) {
        return Integer.parseInt(arg);
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
                        handleNumberArgument(args[argIndex + 1]);
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
