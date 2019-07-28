import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> freq = new HashMap<>();

        for (Character letter : inputSymbols) {
            List<Character> keys = new ArrayList<>(freq.keySet());

            if (!keys.contains(letter)){
                freq.put(letter, 1);
            } else {
                freq.put(letter, freq.get(letter) + 1);
            }
        }
        return freq;
    }

    public static void main(String[] args) {
        String test = "watermelonsugar.txt";
        String filename = test;
        String newFilename = test + ".huf";

        char[] inputSymbols = FileUtils.readFile(filename);
        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);
        BinaryTrie bt = new BinaryTrie(frequencyTable);

        ObjectWriter ow = new ObjectWriter(newFilename);
        ow.writeObject(bt);
        ow.writeObject(inputSymbols.length);

        Map<Character, BitSequence> lookupTable = bt.buildLookupTable();

        List<BitSequence> bitseq = new ArrayList<>();
        for (Character symbol : inputSymbols) {
            bitseq.add(lookupTable.get(symbol));
        }

        BitSequence masterSequence = BitSequence.assemble(bitseq);
        ow.writeObject(masterSequence);
    }
}
