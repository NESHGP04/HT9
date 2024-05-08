import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HuffmanTreeLoader {

    public static HuffmanTree loadTree(String treePath) {
        Map<Character, Integer> frequencies = new HashMap<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(treePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("=")) {
                    String[] parts = line.split("=");
                    char character = parts[0].charAt(0);
                    int frequency = Integer.parseInt(parts[1]);
                    frequencies.put(character, frequency);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the tree file: " + e.getMessage());
            return null;
        }

        HuffmanTree huffmanTree = new HuffmanTree();
        huffmanTree.buildTreeFromFrequencies(frequencies);
        return huffmanTree;
    }
}
