public class HuffmanDecoder {
    public static void main(String[] args) {
        String filename = "watermelonsugar.txt.huf";
        String newFilename = "originalwatermelon.txt";

        ObjectReader or = new ObjectReader(filename);

        Object x = or.readObject();
        Object y = or.readObject();
        Object z = or.readObject();

        BinaryTrie bt;
        int inputSymbolsLength;
        BitSequence masterSequence;

        if (z == null) {
            System.out.println("There is no number of symbols.");
            bt = (BinaryTrie) x;
            masterSequence = (BitSequence) y;
            inputSymbolsLength = masterSequence.length();


        } else {

            bt = (BinaryTrie) x;
            inputSymbolsLength = (int) y;
            masterSequence = (BitSequence) z;

        }
        char[] symbols = new char[inputSymbolsLength];

        for (int i = 0; i < inputSymbolsLength; i += 1) {
            Match match = bt.longestPrefixMatch(masterSequence);
            symbols[i] = match.getSymbol();
            masterSequence = masterSequence.allButFirstNBits(match.getSequence().length());
        }
        FileUtils.writeCharArray(newFilename, symbols);
    }
}
