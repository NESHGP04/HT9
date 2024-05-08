import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTreeTest {
    private HuffmanTree huffmanTree;

    @Before
    public void setUp() {
        huffmanTree = new HuffmanTree();
        Map<Character, Integer> frequencies = new HashMap<>();
        frequencies.put('H', 1);
        frequencies.put('e', 1);
        frequencies.put('l', 3);
        frequencies.put('o', 2);
        frequencies.put(' ', 1);
        frequencies.put('W', 1);
        frequencies.put('r', 1);
        frequencies.put('d', 1);
        huffmanTree.buildTreeFromFrequencies(frequencies);
    }

    @Test
    public void testEncodingDecodingIntegrity() {
        String input = "Hello World";
        String encoded = huffmanTree.code(input);
        String decoded = huffmanTree.decode(encoded, encoded.length());
        assertEquals("The decoded text should match the original", input, decoded);
    }

    @Test
    public void testSpecificEncoding() {
        String expected = "01011101010110000111111000110011"; // this needs to match the actual expected encoding
        String actual = huffmanTree.code("Hello World");
        assertEquals("The encoded text should match the expected binary string", expected, actual);
    }
}
