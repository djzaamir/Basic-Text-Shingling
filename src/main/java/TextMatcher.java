import java.util.ArrayList;

public class TextMatcher {

    private static final int shingleSize = 2;

    public static float match(String a, String b){

      // Text normalization
      String aNormalized = normalizeText(a);
      String bNormalized = normalizeText(b);

      //Generate shingles from both strings
      ArrayList<String> shingles_a = generateShingles(aNormalized);
      ArrayList<String> shingles_b = generateShingles(bNormalized);

      //Merged Shingles
      ArrayList<String> mergedShingles =  new ArrayList<>();
      mergedShingles.addAll(shingles_a);
      mergedShingles.addAll(shingles_b);

     //Generate Main Matrix (The larger original version)
     /*
     *              Document-A  Document-B
     * Shingle-a          0           1
     * Shingle-b          1           0
     * Shingle-c          1           1
     * Shingle-d          1           1
     *
     * */
     int matrixColumnSize = 3; //Depends on the number of the documents + the first column for the actual shingle itself
     ArrayList<ArrayList<String>> sparseMatrix = sparseMatrixGenerator(shingles_a,
                                                                        shingles_b, mergedShingles, matrixColumnSize);

      //Generate random permutations, we would be generating N Permutations


      //Generate reduced Matrix (The smaller compressed version)

      //Calculate Jaccord score from the reduced matrix

      return 0.5f;
    }

    /*
    * TODO
    *  This method could probably be written in a more efficient manner
    *  Right now it has a lot of repetitive for-loops
    * */
    private static ArrayList<ArrayList<String>> sparseMatrixGenerator(ArrayList<String> shingles_a, ArrayList<String> shingles_b, ArrayList<String> mergedShingles, int matrixColumnSize) {
        ArrayList<ArrayList<String>> sparseMatrix= new ArrayList<>();

        //Visit all shingles, and for each shingle make a entry in the sparseMatrix for Document-A and Document-B
        //This entry will indicate the status of the particular shingle in the document-a and document-b.
        for (int i = 0; i < mergedShingles.size(); i++){

            String currentShingle =  mergedShingles.get(i);

            ArrayList<String> entry_for_this_shingle =  new ArrayList<>(matrixColumnSize);

            //Initially consider both document entries as zero
            entry_for_this_shingle.set(0, currentShingle);
            entry_for_this_shingle.set(1, "0");  //for document-a
            entry_for_this_shingle.set(2, "0"); // for document-b



            //First probe the existence of current shingle in Document-A set, then in Document-B set
            for (String shingle_a:
                 shingles_a) {
                if (currentShingle.equals(shingle_a)){
                    entry_for_this_shingle.set(1, "1");
                    break;
                }
            }

            //Now for second document
            for (String shingle_b:
                    shingles_b) {
                if (currentShingle.equals(shingle_b)){
                    entry_for_this_shingle.set(2, "1");
                    break;
                }
            }

            //Put current row-entry inside the main ArrayList
            sparseMatrix.add(entry_for_this_shingle);
        }

        return sparseMatrix;
    }

    private static ArrayList<String> generateShingles(String str) {
        int start_offset = 0;
        int end_offset = start_offset + TextMatcher.shingleSize;

        String[] words = str.split(" ");

        ArrayList<String> shingles = new ArrayList<>();
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
