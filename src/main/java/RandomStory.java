import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

class RandomStory {

    String[] getStory(String filepath, String seed) {
        String[] story = DefaultStory.getStory(filepath);
        String[] randomStory = new String[story.length];
        int lastIndex = story.length - 1;
        List<String> _story = new ArrayList<>();
        for (int i = 0; i < lastIndex; i++) {
            _story.add(story[i]);
        }
        Collections.shuffle(_story, randomSeed(seed));
        _story.add(story[lastIndex]);
        _story.toArray(randomStory);
        return randomStory;
    }

    private Random randomSeed(String seed) {
        return new Random(getSeed(seed));
    }

    private long getSeed(String seed) {
        try {
            return Long.parseLong(seed);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(Constants.ILLEGAL_SEED);
        }
    }

    String seedInfo(String seed) {
        String info = "Seed used - " + getSeed(seed);
        return StringFormatter.addLineFeed(info, Constants.POS_END);
    }
}
