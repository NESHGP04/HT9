import java.util.*;

public class HuffmanTree implements ITree {
    private Node raiz;
    private Map<Character, String> huffmanCodes;

    @Override
    public void buildTree(String text) {
        Map<Character, Integer> frequencies = new HashMap<>();
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

        raiz = cola.poll();
        huffmanCodes = new HashMap<>();
        generarCodigos(raiz, "");
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
    public String decode(String textCoded) {
        StringBuilder resultado = new StringBuilder();
        Node actual = raiz;
        for (int i = 0; i < textCoded.length(); i++) {
            if (textCoded.charAt(i) == '0') {
                actual = actual.left;
            } else {
                actual = actual.right;
            }
            if (actual.left == null && actual.right == null) { // Es hoja
                resultado.append(actual.character);
                actual = raiz;
            }
        }
        return resultado.toString();
    }
}
