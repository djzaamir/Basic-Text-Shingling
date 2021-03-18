import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class main {

    private static String file_a = "news_a.txt";
    private static String file_b = "news_b.txt";
    public static void main(String args[]) throws IOException {

        //Read both files
        String str_file_a = Files.readString(Paths.get(file_a));
        String str_file_b = Files.readString(Paths.get(file_b));


        float matchPercentage = TextMatcher.match(str_file_a, str_file_b);

        System.out.printf("Text-A and Text-B Similarity Score = %.2f %%", matchPercentage);
    }
}
