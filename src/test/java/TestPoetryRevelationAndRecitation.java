import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestPoetryRevelationAndRecitation {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private DefaultReveal revealer = new DefaultReveal();
    private EchoReveal echoRevealer = new EchoReveal();

    private String[] getStory() {

        return DefaultStory.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME);
    }

    private String[] getRandomStory(String seed) {

        return new RandomStory().getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME, seed);
    }

    @Test
    public void testRevelationForRandomDay() {
        int randomDay = (int) (Math.random() * 100);
        Assert.assertNotNull("Poetry revealed for any day is not null", revealer.revealForDayN("" + randomDay, getStory()));
    }

    @Test
    public void testRevelationForDayOne() {
        Assert.assertEquals(1, revealer.revealForDayN("1", getStory()).split("\n").length);
    }

    @Test
    public void testEchoRevelationForDayOne() {
        Assert.assertEquals(2, echoRevealer.revealForDayN("1", getStory()).split("\n").length);
    }

    @Test
    public void testRevelationForDayTwo() {
        Assert.assertEquals(2, revealer.revealForDayN("2", getStory()).split("\n").length);
    }

    @Test
    public void testEchoRevelationForDayTwo() {
        Assert.assertEquals(4, echoRevealer.revealForDayN("2", getStory()).split("\n").length);
    }

    @Test
    public void testRevelationForAnyMidDay() {
        int randomDay = (int) Math.ceil(Math.random() * getStory().length);
        Assert.assertEquals(randomDay, revealer.revealForDayN("" + randomDay, getStory()).split("\n").length);
    }

    @Test
    public void testEchoRevelationForAnyMidDay() {
        int randomDay = (int) Math.ceil(Math.random() * getStory().length);
        Assert.assertEquals(randomDay * 2, echoRevealer.revealForDayN("" + randomDay, getStory()).split("\n").length);
    }

    @Test
    public void testRevelationForLastDay() {
        Assert.assertEquals(getStory().length,
                revealer.revealForDayN(String.valueOf(getStory().length), getStory()).split("\n").length);
    }

    @Test
    public void testEchoRevelationForLastDay() {
        Assert.assertEquals(getStory().length * 2, echoRevealer.revealForDayN(String.valueOf(getStory().length),
                getStory()).split("\n").length);
    }

    @Test
    public void testRevelationForDayGreaterThanStoryLength() {
        int randomDay = (int) (Math.random() * 100) + 12;
        Assert.assertEquals(getStory().length, revealer.revealForDayN("" + randomDay, getStory()).split("\n").length);
    }

    @Test
    public void testEchoRevelationForDayGreaterThanStoryLength() {
        int randomDay = (int) (Math.random() * 100) + 12;
        Assert.assertEquals(getStory().length * 2, echoRevealer.revealForDayN("" + randomDay, getStory()).split("\n").length);
    }

    @Test
    public void testRevelationWithSeedNumber() {
        String[] randomStory = getRandomStory("1");
        Assert.assertTrue(revealer.revealForDayN("12", randomStory).endsWith("the house that Jack built."));
        Assert.assertFalse(revealer.revealForDayN("12", randomStory).equalsIgnoreCase(revealer.revealForDayN("12",
                getStory())));
        Assert.assertEquals(getStory().length,
                revealer.revealForDayN("" + getStory().length, randomStory).split("\n").length);
    }

    @Test
    public void testEchoRevelationWithSeedNumber() {
        String[] randomStory = getRandomStory("1");
        Assert.assertTrue(echoRevealer.revealForDayN("12", randomStory).endsWith("the house that Jack built."));
        Assert.assertFalse(echoRevealer.revealForDayN("12", randomStory).equalsIgnoreCase(echoRevealer.revealForDayN("12",
                getStory())));
        Assert.assertEquals(getStory().length * 2,
                echoRevealer.revealForDayN("" + getStory().length, randomStory).split("\n").length);
    }

    @Test
    public void testRevelationForDayZero() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_DAY);
        revealer.revealForDayN("0", getStory());
    }

    @Test
    public void testEchoRevelationForDayZero() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_DAY);
        echoRevealer.revealForDayN("0", getStory());
    }

    @Test
    public void testForRevealCommandWithIllegalDayNumber() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_DAY);
        revealer.revealForDayN("abc", getStory());
    }

    @Test
    public void testEchoForRevealCommandWithIllegalDayNumber() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_DAY);
        echoRevealer.revealForDayN("abc", getStory());
    }

    @Test
    public void testRevealWithIllegalSeedValue() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_SEED);
        revealer.revealForDayN("1", getRandomStory("abc"));
    }

    @Test
    public void testRevealWithIllegalSeedValueAndIllegalDayNumber() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_SEED);
        revealer.revealForDayN("abc", getRandomStory("abc"));
    }

    @Test
    public void testEchoRevealWithIllegalSeedValue() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_SEED);
        echoRevealer.revealForDayN("1", getRandomStory("abc"));
    }

    @Test
    public void testEchoRevealWithIllegalSeedValueAndIllegalDayNumber() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_SEED);
        echoRevealer.revealForDayN("abc", getRandomStory("abc"));
    }

    @Test
    public void testReciteStory() {
        Recite reciter = new Recite();
        Assert.assertNotNull("Recitation is not null", reciter.recite(getStory(), revealer));
        Assert.assertTrue(reciter.recite(getStory(), revealer).startsWith("Day 1 -"));
        Assert.assertTrue(reciter.recite(getStory(), revealer).split("\n\n").length == getStory().length);
    }

    @Test
    public void testEchoReciteStory() {
        Recite reciter = new Recite();
        Assert.assertNotNull("Recitation is not null", reciter.recite(getStory(), revealer));
        Assert.assertTrue(reciter.recite(getStory(), echoRevealer).startsWith("Day 1 -"));
        Assert.assertTrue(reciter.recite(getStory(), echoRevealer).split("\n\n").length == getStory().length);
    }

    @Test
    public void testReciteStoryWithSeed() {
        Recite reciter = new Recite();
        Assert.assertNotNull("Recitation is not null", reciter.recite(getRandomStory("1"), revealer));
        Assert.assertTrue(reciter.recite(getRandomStory("1"), revealer).endsWith("the house that Jack built.\n\n"));
        Assert.assertTrue(reciter.recite(getRandomStory("1"), revealer).split("\n\n").length == getStory().length);
    }

    @Test
    public void testEchoReciteStorywithSeed() {
        Recite reciter = new Recite();
        Assert.assertNotNull("Recitation is not null", reciter.recite(getRandomStory("1"), echoRevealer));
        Assert.assertTrue(reciter.recite(getRandomStory("1"), echoRevealer).endsWith("the house that Jack built.\n\n"));
        Assert.assertTrue(reciter.recite(getRandomStory("1"), echoRevealer).split("\n\n").length == getStory().length);
    }

    @Test
    public void testReciteStoryWithillegalSeedNumber() {
        Recite reciter = new Recite();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_SEED);
        reciter.recite(getRandomStory("abc"), revealer);
    }

    @Test
    public void testEchoReciteStoryWithillegalSeedNumber() {
        Recite reciter = new Recite();
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(Constants.ILLEGAL_SEED);
        reciter.recite(getRandomStory("abc"), echoRevealer);
    }
}
