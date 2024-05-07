import java.util.Map;

public interface ITree {
    void buildTree(String texto);
    String code(String texto);
    String decode(String textoCodificado, int bitLength);
    void buildTreeFromFrequencies(Map<Character, Integer> frequencies);
    Map<Character, Integer> getFrequencies();
}
