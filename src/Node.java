public class Node {

    private char c;
    private boolean visited;

    public Node(char c){
        this.c = c;
        visited = false;
    }

    public char getC() {
        return c;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }
}
