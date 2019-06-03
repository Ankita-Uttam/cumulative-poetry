import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

abstract class StoryOrder {

//    public static StoryOrder getStoryOrder(String flag) {
//        StoryOrder order = null;
//        switch (flag){
//            case "Random":
//                order = new RandomStory();
//                break;
//            case "None":
//                order = new DefaultStory();
//                break;
//        }
//        return order;
//    }



    abstract String[] getStory(String filepath);
}