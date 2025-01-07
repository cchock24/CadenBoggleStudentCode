// Made by Caden Chock :)
import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        ArrayList<String> goodWords = new ArrayList<String>();

        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.
        int xlength = board.length;
        int ylength = board[0].length;
        Node nodes[][] = new Node[xlength][ylength];
        // Initialize Node Array
        for(int i = 0; i < xlength; i++){
            for(int j = 0; j < ylength; j++){
                nodes[i][j] = new Node(board[i][j]);
            }
        }
        // Make Trie out of Dictionary
        Trie root = new Trie();


        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    // Checks if Square is Empty and in Bounds
    public static boolean checkValid(Node[][] nodes, int x, int y){
        if(x < 0 || x > nodes.length){
            return false;
        }
        if(y < 0 || x > nodes[0].length){
            return false;
        }
        if(nodes[x][y].isVisited()){
            return false;
        }
        return true;
    }

    public void DFS(Node[][] nodes, int x, int y){

    }
    // Based on Code used in SpellCheck
    public void setTrie(String[] dictionary, Trie root){
        Trie start = root;
        for (String s : dictionary) {
            root = start;
            for (int i = 0; i < s.length(); i++) {
                // Get Character's Node (make a = 0)
                int character = s.charAt(i) - 'a';
                if(i == s.length()-1){
                    if(root.getTrie()[character] != null){
                        root.getTrie()[character].setTerminate(true);
                    }
                    else{
                        root.getTrie()[character] = new Trie(true);
                    }
                }
                else{
                    if(root.getTrie()[character] == null){
                        root.getTrie()[character] = new Trie();
                    }
                }
                root = root.getTrie()[character];
            }
        }
    }

    public boolean checkTrie(String s, Trie root){
        char c;
        int character;
        for(int i = 0; i < s.length(); i++){
            character = s.charAt(i) - 'a';
            if(root.getTrie()[character] == null){
                return false;
            }
            if(root.getTrie()[character].isTerminate() && i == s.length()-1){
                return true;
            }
            root = root.getTrie()[character];
        }
        return false;
    }
}
