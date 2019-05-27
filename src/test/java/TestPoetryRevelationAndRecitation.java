import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class TestPoetryRevelationAndRecitation {

    private ReadingFormat format = new ReadingFormat();

    private String[] getStory() {
        String[] story = new String[12];
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
        Assert.assertNotNull("Poetry revealed for any day is not null", format.revealForDayN(randomDay, getStory()));
    }

    @Test
    public void testRevelationForDayOne() {
        Assert.assertEquals("This is the house that Jack built.", format.revealForDayN(1, getStory()));
    }

    @Test
    public void testRevelationForDayTwo() {
        Assert.assertEquals("This is the malt that lay in\n\tthe house that Jack built.", format.revealForDayN(2, getStory()));
    }

    @Test
    public void testRevelationForDayThree() {
        Assert.assertEquals("This is the rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(3, getStory()));
    }

    @Test
    public void testRevelationForDayFour() {
        Assert.assertEquals("This is the cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(4, getStory()));
    }

    @Test
    public void testRevelationForDayFive() {
        Assert.assertEquals("This is the dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(5, getStory()));
    }

    @Test
    public void testRevelationForDaySix() {
        Assert.assertEquals("This is that cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(6, getStory()));
    }

    @Test
    public void testRevelationForDaySeven() {
        Assert.assertEquals("This is the maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(7, getStory()));
    }

    @Test
    public void testRevelationForDayEight() {
        Assert.assertEquals("This is the man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(8, getStory()));
    }

    @Test
    public void testRevelationForDayNine() {
        Assert.assertEquals("This is the priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(9, getStory()));
    }

    @Test
    public void testRevelationForDayTen() {
        Assert.assertEquals("This is the rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(10, getStory()));
    }

    @Test
    public void testRevelationForDayEleven() {
        Assert.assertEquals("This is the farmer sowing his corn that kept\n\tthe rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(11, getStory()));
    }

    @Test
    public void testRevelationForDayTwelve() {
        Assert.assertEquals("This is the horse and the hound and the horn that belonged to\n\tthe farmer sowing his corn that kept\n\tthe rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(12, getStory()));
    }

    @Test
    public void testRevelationForDayGreaterThanTwelve() {
        int randomDayGreaterThan12 = (int) (Math.random() * 100) + 12;
        Assert.assertEquals("This is the horse and the hound and the horn that belonged to\n\tthe farmer sowing his corn that kept\n\tthe rooster that crowed in the morn that woke\n\tthe priest all shaven and shorn that married\n\tthe man all tattered and torn that kissed\n\tthe maiden all forlorn that milked\n\tthat cow with the crumpled horn that tossed\n\tthe dog that worried\n\tthe cat that killed\n\tthe rat that ate\n\tthe malt that lay in\n\tthe house that Jack built.", format.revealForDayN(randomDayGreaterThan12, getStory()));
    }

    @Test
    public void testRevelationForDayZero() {
        Assert.assertEquals("No story for day 0", format.revealForDayN(0, getStory()));
    }

    @Test
    public void testReciteStory() {
        Assert.assertNotNull("Recitation is not null", format.recite(getStory()));
    }
}
