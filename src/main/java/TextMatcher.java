import java.util.ArrayList;

public class TextMatcher {

    private static final int shingleSize = 2;

    public static float match(String a, String b){

      // Transform string to lowercase
      String aNormalized = normalizeText(a);
      String bNormalized = normalizeText(b);

      //Generate shingles from both strings
      ArrayList<String> shingles = generateShingles(aNormalized);
      //To generate shingles from other set, we would be passing previous shingle set in
     // Because we want to combine both sets for further computation
      generateShingles(bNormalized, shingles);

     //Generate Main Matrix (The larger original version)

      //Generate random permutations, we would be generating N Permutations


      //Generate reduced Matrix (The smaller compressed version)

      //Calculate Jaccord score from the reduced matrix

      return 0.5f;
    }

    private static ArrayList<String> generateShingles(String str, ArrayList<String> shingles) {
        int start_offset = 0;
        int end_offset = start_offset + TextMatcher.shingleSize;

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

    private static ArrayList<String> generateShingles(String str) {
         //Create a new required Shingle ArrayList and pass it into overloaded generateShingle method
        // Doing so because this because both methods are doing exactly the same thing
       //  The only difference, one is creating the ArrayList internally and other accepting as a parameter
      //   So simply it, i've delegated the ArrayList creation process to this overload, and remaining process to other
       return generateShingles(str, new ArrayList<String>());
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
