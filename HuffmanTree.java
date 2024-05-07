import java.util.*;

public class HuffmanTree implements ITree {
    private Node root;
    private Map<Character, String> huffmanCodes;
    private Map<Character, Integer> frequencies = new HashMap<>();

    @Override
    public void buildTree(String text) {
        for (char character : text.toCharArray()) {
            frequencies.put(character, frequencies.getOrDefault(character, 0) + 1);
        }

        PriorityQueue<Node> cola = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            cola.add(new Node(entry.getKey(), entry.getValue()));
        }

        while (cola.size() > 1) {
            Node left = cola.poll();
            Node right = cola.poll();
            Node padre = new Node(left, right);
            cola.add(padre);
        }

        root = cola.poll();
        huffmanCodes = new HashMap<>();
        generarCodigos(root, "");
    }

    private void generarCodigos(Node nodo, String codigo) {
        if (nodo != null) {
            if (nodo.left == null && nodo.right == null) { // Es hoja
                huffmanCodes.put(nodo.character, codigo);
            }
            generarCodigos(nodo.left, codigo + "0");
            generarCodigos(nodo.right, codigo + "1");
        }
    }

    @Override
    public String code(String text) {
        StringBuilder codigo = new StringBuilder();
        for (char character : text.toCharArray()) {
            codigo.append(huffmanCodes.get(character));
        }
        return codigo.toString();
    }

    @Override
    public String decode(String encodedText, int bitLength) {
        StringBuilder result = new StringBuilder();
        Node current = root;
        int count = 0;
    
        for (int i = 0; i < bitLength; i++) {
            current = encodedText.charAt(i) == '1' ? current.right : current.left;
    
            if (current.left == null && current.right == null) {
                result.append(current.character);
                current = root;
            }
        }
    
        return result.toString();
    }

    @Override
    public void buildTreeFromFrequencies(Map<Character, Integer> frequencies) {
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
            queue.add(new Node(entry.getKey(), entry.getValue()));
        }
    
        while (queue.size() > 1) {
            Node left = queue.poll();
            Node right = queue.poll();
            Node parent = new Node(left, right);
            queue.add(parent);
        }
    
        this.root = queue.poll();
        this.huffmanCodes = new HashMap<>();
        generarCodigos(this.root, "");
    }    

    @Override
    public Map<Character, Integer> getFrequencies() {
        return this.frequencies; 
    }
}
