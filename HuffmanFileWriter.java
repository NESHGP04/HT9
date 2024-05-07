import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class HuffmanFileWriter {

    public static void writeEncodedToFile(String encodedText, String outputPath) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(outputPath))) {
            //Escribe la longitud del texto codificado
            dos.writeInt(encodedText.length());
            int bitBuffer = 0;
            int bitCount = 0;

            for (int i = 0; i < encodedText.length(); i++) {
                bitBuffer = (bitBuffer << 1) | (encodedText.charAt(i) == '1' ? 1 : 0);
                bitCount++;

                if (bitCount == 8) {
                    dos.write(bitBuffer);
                    bitBuffer = 0;
                    bitCount = 0;
                }
            }

            if (bitCount > 0) {
                bitBuffer <<= (8 - bitCount); 
                dos.write(bitBuffer);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
