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
        return PoetryReader.getStory(Constants.RESOURCE_PATH + Constants.FILE_NAME);
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
    public void testRevelationForDayZero() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: illegal day number");
        revealer.revealForDayN("0", getStory());
    }

    @Test
    public void testEchoRevelationForDayZero() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: illegal day number");
        echoRevealer.revealForDayN("0", getStory());
    }

    @Test
    public void testForRevealCommandWithIllegalDayNumber() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: illegal day number");
        revealer.revealForDayN("abc", getStory());
    }

    @Test
    public void testEchoForRevealCommandWithIllegalDayNumber() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: illegal day number");
        echoRevealer.revealForDayN("abc", getStory());
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
}
