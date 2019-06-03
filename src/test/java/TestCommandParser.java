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
        Assert.assertEquals("20", parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
    }

    @Test
    public void testForRevealingCommandWithEcho() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "20"};
        Assert.assertEquals(Constants.REVEAL, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals("20", parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER));
        Assert.assertEquals(Constants.FLAG_ECHO, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
    }

    @Test
    public void testForRecitingCommand() {
        String[] args = {Constants.RECITE_IDENTIFIER};
        Assert.assertEquals(Constants.RECITE, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
    }

    @Test
    public void testForRecitingCommandWithEcho() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER};
        Assert.assertEquals(Constants.RECITE, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals(Constants.FLAG_ECHO, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
    }

    @Test
    public void testForRandomReciteWithoutSeed() {
        String[] args = {Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER};
        Assert.assertEquals(Constants.RECITE, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals(Constants.DEFAULT_SEED, parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForEchoRandomReciteWithoutSeed() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER};
        Assert.assertEquals(Constants.RECITE, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals(Constants.FLAG_ECHO, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals(Constants.DEFAULT_SEED, parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForRandomReciteWithSeed() {
        String[] args = {Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER, Constants.SEED_IDENTIFIER, "10"};
        Assert.assertEquals(Constants.RECITE, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals("10", parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForEchoRandomReciteWithSeed() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER,
                Constants.SEED_IDENTIFIER, "10"};
        Assert.assertEquals(Constants.RECITE, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals(Constants.FLAG_ECHO, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals("10", parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForRandomRevealWithoutSeed() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "5", Constants.RANDOM_IDENTIFIER};
        Assert.assertEquals(Constants.REVEAL, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals("5", parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals(Constants.DEFAULT_SEED, parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForRandomRevealWithSeed() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "5", Constants.RANDOM_IDENTIFIER, Constants.SEED_IDENTIFIER,
                "10"};
        Assert.assertEquals(Constants.REVEAL, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals("5", parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER));
        Assert.assertEquals(Constants.FLAG_NONE, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals("10", parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForEchoRandomRevealWithSeed() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "5", Constants.RANDOM_IDENTIFIER,
                Constants.SEED_IDENTIFIER,
                "10"};
        Assert.assertEquals(Constants.REVEAL, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals("5", parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER));
        Assert.assertEquals(Constants.FLAG_ECHO, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals("10", parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForEchoRandomRevealWithoutSeed() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "5", Constants.RANDOM_IDENTIFIER};
        Assert.assertEquals(Constants.REVEAL, parser.getParsedCommandMap(args).get(Constants.KEY_ACTION));
        Assert.assertEquals("5", parser.getParsedCommandMap(args).get(Constants.KEY_DAY_NUMBER));
        Assert.assertEquals(Constants.FLAG_ECHO, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_FORMAT));
        Assert.assertEquals(Constants.FLAG_RANDOM, parser.getParsedCommandMap(args).get(Constants.KEY_FLAG_ORDER));
        Assert.assertEquals(Constants.DEFAULT_SEED, parser.getParsedCommandMap(args).get(Constants.KEY_SEED));
    }

    @Test
    public void testForIllegalCommand() {
        String[] args = {"--i-am-illegal"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForIllegalCommandWithDayNumber() {
        String[] args = {"--i-am-illegal", "20"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForIllegalCommandWithMoreArguments() {
        String[] args = {"--i-am-illegal", "illegal", "cmd"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForIllegalCommandWithEcho() {
        String[] args = {Constants.ECHO_IDENTIFIER,"--i-am-illegal"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForRevealCommandWithMoreArguments() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "20", "some", "other", "arguments", "passed", "to", "cmd"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.EXTRA_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForRevealCommandAndEchoWithMoreArguments() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "20", "some", "other", "arguments", "passed", "to", "cmd"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.EXTRA_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForReciteCommandWithMoreArguments() {
        String[] args = {Constants.RECITE_IDENTIFIER, "20", "some", "other", "arguments", "passed", "to", "cmd"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.EXTRA_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForReciteCommandWithMoreArgumentsWithEcho() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, "20", "some", "other", "arguments",
                "passed", "to", "cmd"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.EXTRA_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForRevealCommandWithIllegalDayNumberAndMoreArguments() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "abc", "some", "other", "arguments", "in", "cmd"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.EXTRA_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testForCommandWithoutArguments() {
        String[] args = {};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.NO_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalEcho() {
        String[] args = {"--not-echo"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalFormatWithEcho() {
        String[] args = {Constants.ECHO_IDENTIFIER, "--illegal-format"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testRevealWithoutDayNumber() {
        String[] args = {Constants.REVEAL_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.INSUFFICIENT_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testEchoRevealWithoutDayNumber() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.INSUFFICIENT_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalRandomOptionWithReveal() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "10", "--illegal-random"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalRandomOptionWithEchoReveal() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "10", "--illegal-random"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalRandomOptionWithRecite() {
        String[] args = {Constants.RECITE_IDENTIFIER, "--illegal-random"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalRandomOptionWithEchoRecite() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, "--illegal-random"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalSeedWithRecite() {
        String[] args = {Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER,
        "--illegal-seed"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalSeedWithEchoRecite() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER,
                "--illegal-seed"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalSeedWithReveal() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "10", Constants.RANDOM_IDENTIFIER,
                "--illegal-seed"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testIllegalSeedWithEchoReveal() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "10", Constants.RANDOM_IDENTIFIER,
                "--illegal-seed"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testRevealSeedWithoutSeedValue() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "10", Constants.RANDOM_IDENTIFIER, Constants.SEED_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.INSUFFICIENT_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testEchoRevealSeedWithoutSeedValue() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "10", Constants.RANDOM_IDENTIFIER,
                Constants.SEED_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.INSUFFICIENT_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testReciteSeedWithoutSeedValue() {
        String[] args = {Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER, Constants.SEED_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.INSUFFICIENT_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testEchoReciteSeedWithoutSeedValue() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, Constants.RANDOM_IDENTIFIER,
                Constants.SEED_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.INSUFFICIENT_ARGS);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testDayNumberWithoutFormat() {
        String[] args = {"10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testDayNumberWithEchoWithoutFormat() {
        String[] args = {Constants.ECHO_IDENTIFIER,"10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testDayNumberWithRecite() {
        String[] args = {Constants.RECITE_IDENTIFIER,"10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testDayNumberWithEchoRecite() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, "10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testRandomWithoutFormat() {
        String[] args = {Constants.RANDOM_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testRandomWithEchoWithoutFormat() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RANDOM_IDENTIFIER};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testSeedWithoutFormatAndRandom() {
        String[] args = {Constants.SEED_IDENTIFIER, "10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testSeedWithEchoWithoutFormatAndRandom() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.SEED_IDENTIFIER, "10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testSeedWithEchoReciteWithoutRandom() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.RECITE_IDENTIFIER, Constants.SEED_IDENTIFIER, "10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testSeedWithReciteWithoutRandom() {
        String[] args = {Constants.RECITE_IDENTIFIER,Constants.SEED_IDENTIFIER, "10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testSeedWithEchoRevealWithoutRandom() {
        String[] args = {Constants.ECHO_IDENTIFIER, Constants.REVEAL_IDENTIFIER, "10", Constants.SEED_IDENTIFIER, "10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

    @Test
    public void testSeedWithRevealWithoutRandom() {
        String[] args = {Constants.REVEAL_IDENTIFIER, "10", Constants.SEED_IDENTIFIER, "10"};
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.CMD_NOT_FOUND);
        parser.getParsedCommandMap(args);
    }

}
