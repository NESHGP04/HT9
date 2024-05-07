import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Nombre del archivo de texto a leer
        String nombreArchivo = "texto_prueba.txt";

        try {
            // Leer el archivo de texto
            String texto = leerArchivo(nombreArchivo);

            // Instancia del árbol de Huffman
            HuffmanTree arbolHuffman = new HuffmanTree();

            // Construir el árbol de Huffman basado en el texto leído del archivo
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
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    // Método para leer el contenido de un archivo de texto
    private static String leerArchivo(String nombreArchivo) throws IOException {
        StringBuilder sb = new StringBuilder();
        File archivo = new File(nombreArchivo);
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                sb.append(linea);
                sb.append("\n"); // Agregar salto de línea para mantener el formato del texto original
            }
        }
        return sb.toString();
    }
}
