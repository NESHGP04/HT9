import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class HuffmanFileReader {

    static class HuffmanReadResult {
        String encodedText;
        int bitLength;

        HuffmanReadResult(String encodedText, int bitLength) {
            this.encodedText = encodedText;
            this.bitLength = bitLength;
        }
    }

    public static HuffmanReadResult readEncodedFromFile(String inputPath) {
        StringBuilder sb = new StringBuilder();
        int bitLength = 0;
        try (DataInputStream dis = new DataInputStream(new FileInputStream(inputPath))) {
            bitLength = dis.readInt(); // Leer la longitud del texto codificado
            int bytes;
            while ((bytes = dis.read()) != -1) {
                sb.append(String.format("%8s", Integer.toBinaryString(bytes & 0xFF)).replace(' ', '0'));
            }
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
            return null;
        }
        return new HuffmanReadResult(sb.toString(), bitLength);
    }
}
