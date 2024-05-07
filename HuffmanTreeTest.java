import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class HuffmanTreeTest {

    @Test
    public void generarCodigosTest() {
        HuffmanTree huffmanTree = new HuffmanTree();
        String input = "Hello, World!";
        String expected = "0100100001100101011011000110110001101111";
        String actual = huffmanTree.encode(input);
        assertEquals(expected, actual);
    }
}