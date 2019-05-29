public class StringFormatter {

    public static String addLineFeed(String str, String position) {
        String formattedString = str;
        if (position.equals(Constants.FORMAT_POS_END))
            formattedString = str + "\n";
        else if (position.equals(Constants.FORMAT_POS_START))
            formattedString = "\n" + str;
        return formattedString;
    }

    public static String addTab(String str, String position) {
        String formattedString = str;
        if (position.equals(Constants.FORMAT_POS_END))
            formattedString = str + "\t";
        else if (position.equals(Constants.FORMAT_POS_START))
            formattedString = "\t" + str;
        return formattedString;
    }
}
