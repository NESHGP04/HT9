import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    private static final String FILE_PATH = "texto.txt";
    private static final String HUFF_PATH = "texto.huff";
    private static final String TREE_PATH = "texto.tree";
    private static final HuffmanTree huffmanTree = new HuffmanTree();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option = 0;

        while (option != 3) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Comprimir texto.txt");
            System.out.println("2. Descomprimir texto.huff");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    compressText();
                    break;
                case 2:
                    decompressText();
                    break;
                case 3:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida, intente nuevamente.");
            }
        }

        scanner.close();
    }

    private static void compressText() {
        String texto = FileReaderUtil.readFileToString(FILE_PATH);
        if (texto == null) {
            System.out.println("No se pudo leer el archivo.");
            return;
        }

        huffmanTree.buildTree(texto);

        String textoCodificado = huffmanTree.code(texto);
        System.out.println("Texto codificado: " + textoCodificado);

        HuffmanTreeSaver.saveTree(huffmanTree.getFrequencies(), TREE_PATH);
        System.out.println("Árbol guardado en: " + TREE_PATH);

        HuffmanFileWriter.writeEncodedToFile(textoCodificado, HUFF_PATH);
        System.out.println("Texto codificado guardado en: " + HUFF_PATH);

        // Calcular y mostrar el ratio de compresión
        try {
            long originalSize = Files.size(Paths.get(FILE_PATH));
            long compressedSize = Files.size(Paths.get(HUFF_PATH));
            double ratio = (double) compressedSize / originalSize;
            System.out.println("Ratio de compresión: " + compressedSize + " / " + originalSize + " = " + ratio);
        } catch (IOException e) {
            System.err.println("Error al calcular el ratio de compresión: " + e.getMessage());
        }
    }

    private static void decompressText() {
        // Cargar el árbol desde el archivo .tree
        HuffmanTree huffmanTree = HuffmanTreeLoader.loadTree(TREE_PATH);
        if (huffmanTree == null) {
            System.out.println("No se pudo cargar el árbol de Huffman.");
            return;
        }
    
        // Leer el archivo .huff
        HuffmanFileReader.HuffmanReadResult readResult = HuffmanFileReader.readEncodedFromFile(HUFF_PATH);
        if (readResult == null) {
            System.out.println("No se pudo leer el archivo .huff.");
            return;
        }
    
        String textoCodificadoLeido = readResult.encodedText;
        int bitLength = readResult.bitLength;
        System.out.println("Texto codificado leído: " + textoCodificadoLeido);
    
        // Decodificar el texto usando la longitud de bits exacta
        String textoDecodificado = huffmanTree.decode(textoCodificadoLeido, bitLength);
        System.out.println("Texto decodificado: " + textoDecodificado);
    }    
}
