public class Main {
    public static void main(String[] args) {
        // Texto de ejemplo para la compresión
        String texto = "Marines";

        // Instancia del árbol de Huffman
        HuffmanTree arbolHuffman = new HuffmanTree();

        // Construir el árbol de Huffman basado en el texto de entrada
        arbolHuffman.buildTree(texto);

        // Codificar el texto usando el árbol de Huffman
        String textoCodificado = arbolHuffman.code(texto);
        System.out.println("Texto codificado: " + textoCodificado);

        // Decodificar el texto usando el árbol de Huffman
        String textoDecodificado = arbolHuffman.decode(textoCodificado);
        System.out.println("Texto decodificado: " + textoDecodificado);

        // Comparar el texto decodificado con el original
        if (texto.equals(textoDecodificado)) {
            System.out.println("El texto decodificado coincide con el texto original.");
        } else {
            System.out.println("Hay un error, el texto decodificado no coincide con el original.");
        }
    }
}
