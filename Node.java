public class Node implements Comparable<Node> {
    char character;
    int frequency;
    Node left;
    Node right;

    public Node(char character, int frequency) {
        this.character = character;
        this.frequency = frequency;
        this.left = null;
        this.right = null;
    }

    public Node(Node left, Node right) {
        this.frequency = left.frequency + right.frequency;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(Node that) {
        return this.frequency - that.frequency;
    }
}
