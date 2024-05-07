import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

public class HuffmanTreeSaver {
    
    public static void saveTree(Map<Character, Integer> frequencies, String outputPath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
                // Escritura del carácter y su frecuencia en el archivo
                writer.write(entry.getKey() + "=" + entry.getValue() + "\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving the tree to file: " + e.getMessage());
        }
    }
}
