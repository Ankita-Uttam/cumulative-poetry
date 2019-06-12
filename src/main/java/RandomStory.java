import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Random;

class RandomStory extends StoryOrder{

    private int seedValue;

    RandomStory(Integer seedValue) {
        this.seedValue = seedValue;
    }

    String[] getStory(String filepath) throws FileNotFoundException {
        String[] story = getFileContents(filepath);
        String[] randomStory = new String[story.length];
        int lastIndex = story.length - 1;
        List<String> _story = new ArrayList<>();
        for (int i = 0; i < lastIndex; i++) { // TODO - too much implicit logic
            _story.add(story[i]);
        }
        Collections.shuffle(_story, randomSeed());
        _story.add(story[lastIndex]);
        _story.toArray(randomStory);
        System.out.println(randomStory);
        return randomStory;
    }

    private Random randomSeed() {
        return new Random(seedValue);
    }

    String seedInfo(String seed) {
        String info = Constants.SEED_INFO_FORMAT + seedValue;
        return StringFormatter.addLineFeed(info, Constants.POS_END);
    }
}
