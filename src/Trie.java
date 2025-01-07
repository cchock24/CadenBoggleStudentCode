public class Trie {
    Trie[] tries;
    boolean terminate;

    public Trie() {
        tries = new Trie[26];
        terminate = false;
    }

    public Trie(boolean t) {
        tries = new Trie[26];
        terminate = t;
    }

    public Trie[] getTrie() {
        return tries;
    }

    public void setTrie(Trie[] nodes) {
        this.tries = nodes;
    }

    public boolean isTerminate() {
        return terminate;
    }

    public void setTerminate(boolean terminate) {
        this.terminate = terminate;
    }
}
