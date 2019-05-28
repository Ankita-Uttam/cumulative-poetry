import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestCommandParser {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private CommandParser parser = new CommandParser();

    @Test
    public void testForRevealingCommand() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "20"};
        Assert.assertEquals(Constants.REVEAL, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
    }

    @Test
    public void testForRecitingCommand() {
        String[] args = {Constants.RECITE_IDENTIFIER};
        Assert.assertEquals(Constants.RECITE, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
    }

    @Test
    public void testForIllegalCommand() {
        String[] args = {"--i-am-illegal"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("PoetryReader: --i-am-illegal: command not found");
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForIllegalCommandWithDayNumber() {
        String[] args = {"--i-am-illegal", "20"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("PoetryReader: --i-am-illegal: command not found");
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForIllegalCommandWithMoreArguments() {
        String[] args = {"--i-am-illegal"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("PoetryReader: --i-am-illegal: command not found");
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForRevealCommandWithMoreArguments() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "20", "some", "other", "arguments"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: unnecessary arguments");
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForReciteCommandWithMoreArguments() {
        String[] args = {Constants.RECITE_IDENTIFIER, "20", "some", "other", "arguments"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong recite command: unnecessary arguments");
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForRevealCommandWithIllegalDayNumberAndMoreArguments() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "abc", "some", "other", "arguments"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: unnecessary arguments");
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForCommandWithoutArguments() {
        String[] args = {};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("expected arguments: no arguments received");
        parser.getParsedCommandMap(args);
    }
}
