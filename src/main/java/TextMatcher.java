import java.util.ArrayList;

public class TextMatcher {

    private static int shingleSize = 3;
    public static float match(String a, String b){

      // Transform string to lowercase
      String aNormalized = normalizeText(a);
      String bNormalized = normalizeText(b);

      //Generate shingles from both strings
      ArrayList<String> shingles_a = generateShingles(aNormalized ,shingleSize);
      ArrayList<String> shingles_b = generateShingles(bNormalized, shingleSize);

      int totalShingles = shingles_a.size() + shingles_b.size();
      int matchedShingles = 0;

      //Try to match every shingle with every other shingle in order to find a matching pair
      for (int i = 0 ; i < shingles_a.size(); i++){
          for (int j = 0 ; j < shingles_b.size(); j++){

              //Try to match exact shingles
              if (shingles_a.get(i).equals(shingles_b.get(j))){
                  matchedShingles++;
              }
          }
      }

      return ((float)matchedShingles / totalShingles) * 100;
    }

    private static ArrayList<String> generateShingles(String str, int shingleSize) {
        int start_offset = 0;
        int end_offset = start_offset + shingleSize;

        ArrayList<String> tr =  new ArrayList<>();

        String[] words = str.split(" ");

        //Extract shingles
        while (end_offset <= words.length){
            String shingle = extractNWords(words, start_offset, end_offset);
            tr.add(shingle);

            //move shingle bracket to next K pairs
            start_offset++;
            end_offset++;
        }

        return tr;
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
