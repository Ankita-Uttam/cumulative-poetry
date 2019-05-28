import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestPoetryRevelationAndRecitation {

    @Rule
    public final ExpectedException exception = ExpectedException.none();
    private Reveal reveal = new Reveal();

    private String[] getStory() {
        String[] story = new String[Constants.MAX_LINES];
        try {
            FileInputStream inputStream = new FileInputStream("/Users/ankita.uttam/practice/Java/cumulative-poetry/src/main/resources/poetry.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String storyline;
            int lineIndex = 0;
            while ((storyline = br.readLine()) != null) {
                story[lineIndex++] = storyline;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return story;
    }

    @Test
    public void testRevelationForRandomDay() {
        int randomDay = (int) (Math.random() * 100);
        Assert.assertNotNull("Poetry revealed for any day is not null", reveal.revealForDayN("" + randomDay, getStory()));
    }

    @Test
    public void testRevelationForDayOne() {
        Assert.assertEquals("This is the house that Jack built.", reveal.revealForDayN("1", getStory()));
    }

    @Test
    public void testRevelationForDayTwo() {
        Assert.assertEquals("This is the malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("2", getStory()));
    }

    @Test
    public void testRevelationForDayThree() {
        Assert.assertEquals("This is the rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("3", getStory()));
    }

    @Test
    public void testRevelationForDayFour() {
        Assert.assertEquals("This is the cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("4", getStory()));
    }

    @Test
    public void testRevelationForDayFive() {
        Assert.assertEquals("This is the dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("5", getStory()));
    }

    @Test
    public void testRevelationForDaySix() {
        Assert.assertEquals("This is that cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("6", getStory()));
    }

    @Test
    public void testRevelationForDaySeven() {
        Assert.assertEquals("This is the maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("7", getStory()));
    }

    @Test
    public void testRevelationForDayEight() {
        Assert.assertEquals("This is the man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("8", getStory()));
    }

    @Test
    public void testRevelationForDayNine() {
        Assert.assertEquals("This is the priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("9", getStory()));
    }

    @Test
    public void testRevelationForDayTen() {
        Assert.assertEquals("This is the rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("10", getStory()));
    }

    @Test
    public void testRevelationForDayEleven() {
        Assert.assertEquals("This is the farmer sowing his corn that kept\n\tthe rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("11", getStory()));
    }

    @Test
    public void testRevelationForDayTwelve() {
        Assert.assertEquals("This is the horse and the hound and the horn that belonged to\n\tthe farmer sowing his corn that kept\n\tthe rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("12", getStory()));
    }

    @Test
    public void testRevelationForDayGreaterThanTwelve() {
        int randomDayGreaterThan12 = (int) (Math.random() * 100) + 12;
        Assert.assertEquals("This is the horse and the hound and the horn that belonged to\n\tthe farmer sowing his corn that kept\n\tthe rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", reveal.revealForDayN("" + randomDayGreaterThan12, getStory()));
    }

    @Test
    public void testRevelationForDayZero() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: illegal day number");
        reveal.revealForDayN("0", getStory());
    }

    @Test
    public void testForRevealCommandWithIllegalDayNumber() {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage("wrong reveal command: illegal day number");
        reveal.revealForDayN("abc", getStory());
    }

    @Test
    public void testReciteStory() {
        Assert.assertNotNull("Recitation is not null", reveal.recite(getStory()));
        Assert.assertTrue(reveal.recite(getStory()).startsWith("Day 1 -"));
        Assert.assertTrue(reveal.recite(getStory()).split("\n\n").length == Constants.LAST_DAY);
    }
}
