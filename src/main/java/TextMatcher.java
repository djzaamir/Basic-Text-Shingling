import java.util.ArrayList;

public class TextMatcher {

    private static int shingleSize = 2;

    public static float match(String a, String b){

      // Transform string to lowercase
      String aNormalized = normalizeText(a);
      String bNormalized = normalizeText(b);

      //Generate shingles from both strings
      ArrayList<String> shingles = generateShingles(aNormalized ,shingleSize);
      //To generate shingles from other set, we would be passing previous shingle set in
     // Because we want to combine both sets for further computation
      generateShingles(bNormalized, shingleSize, shingles);

      int totalShingles = shingles.size();
      int matchedShingles = 0;

      //Generate random permutations

      return ((float)matchedShingles / totalShingles) * 100;
    }

    private static ArrayList<String> generateShingles(String str, int shingleSize, ArrayList<String> shingles) {
        int start_offset = 0;
        int end_offset = start_offset + shingleSize;

        String[] words = str.split(" ");

        //Extract shingles
        while (end_offset <= words.length){
            String shingle = extractNWords(words, start_offset, end_offset);
            shingles.add(shingle);

            //move shingle bracket to next K pairs
            start_offset++;
            end_offset++;
        }
        return shingles;
    }

    private static ArrayList<String> generateShingles(String str, int shingleSize) {
         //Create a new required Shingle ArrayList and pass it into overloaded generateShingle method
        // Doing so because this because both methods are doing exactly the same thing
       //  The only difference, one is creating the ArrayList internally and other accepting as a parameter
      //   So simply it, i've delegated the ArrayList creation process to this overload, and remaining process to other
       return generateShingles(str, shingleSize, new ArrayList<String>());
    }

    private static String extractNWords(String[] words, int start_offset, int end_offset) {
        StringBuilder wordsBuilder = new StringBuilder();

        for (int i = start_offset; i < end_offset; i++)
            wordsBuilder.append(words[i]);

        return wordsBuilder.toString();
    }

    private static String normalizeText(String inputStr){
        // Transform string to lowercase & remove newline character
        return inputStr
                .toLowerCase()
                .replaceAll("\n", "");
    }
}
