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
        String[] args = {"--reveal-for-day", "20"};
        Assert.assertEquals("Reveal", parser.getParsedCommandMap(args).get("Action"));
    }

    @Test
    public void testForRecitingCommand() {
        String[] args = {"--recite"};
        Assert.assertEquals("Recite", parser.getParsedCommandMap(args).get("Action"));
    }

//    @Test
//    public void testForIllegalCommand() {
//        String[] args = {"--i-am-illegal"};
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("PoetryReader: --i-am-illegal: command not found");
//        parser.getParsedCommandMap(args);
//    }
//
//    @Test
//    public void testForIllegalCommandWithDayNumber() {
//        String[] args = {"--i-am-illegal", "20"};
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("PoetryReader: --i-am-illegal: command not found");
//        parser.getParsedCommandMap(args);
//    }
//
//    @Test
//    public void testForIllegalCommandWithMoreArguments() {
//        String[] args = {"--i-am-illegal"};
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("PoetryReader: --i-am-illegal: command not found");
//        parser.getParsedCommandMap(args);
//    }
//
//    @Test
//    public void testForRevealCommandWithMoreArguments() {
//        String[] args = {"--reveal-for-day", "20", "some", "other", "arguments"};
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("wrong reveal command: unnecessary arguments");
//        parser.getParsedCommandMap(args);
//    }
//
//    @Test
//    public void testForReciteCommandWithMoreArguments() {
//        String[] args = {"--recite", "20", "some", "other", "arguments"};
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("wrong recite command: unnecessary arguments");
//        parser.getParsedCommandMap(args);
//    }
//
//    @Test
//    public void testForRevealCommandWithIllegalDayNumber() {
//        String[] args = {"--reveal-for-day", "abc"};
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("wrong reveal command: illegal day number");
//        parser.getParsedCommandMap(args);
//    }
//
//    @Test
//    public void testForRevealCommandWithIllegalDayNumberAndMoreArguments() {
//        String[] args = {"--reveal-for-day", "abc", "some", "other", "arguments"};
//        exception.expect(IllegalArgumentException.class);
//        exception.expectMessage("wrong reveal command: illegal day number");
//        parser.getParsedCommandMap(args);
//    }
}
