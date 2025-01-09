// Made by Caden Chock :)
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        HashSet<String> goodWords = new HashSet<String>();

        // TODO: Complete the function findWords(). Add all words that are found both on the board
        //  and in the dictionary.
        int x = board.length;
        int y = board[0].length;
        Node[][] nodes = new Node[x][y];
        // Initialize Node Array
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                nodes[i][j] = new Node(board[i][j]);
            }
        }
        // Make Trie out of Dictionary
        Trie root = new Trie();
        setTrie(dictionary, root);

        // Iterate Through Board
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                DFS(nodes, i,j,root,"",goodWords);
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    // Checks if Square is Empty and in Bounds
    public static boolean checkValid(Node[][] nodes, int x, int y){
        if(x < 0 || x >= nodes.length){
            return false;
        }
        if(y < 0 || y >= nodes[0].length){
            return false;
        }
        if(nodes[x][y].isVisited()){
            return false;
        }
        return true;
    }

    public static void DFS(Node[][] nodes, int x, int y, Trie root, String prefix, HashSet<String> goodWords){
        prefix = prefix + nodes[x][y].getC();
        //Return Case
        //Can't Find in Dictionary
        if(!checkPre(prefix, root)){
            return;
        }
        //Mark as Visited
        nodes[x][y].setVisited(true);
        //Check if Word
        if(checkTrie(prefix,root)){
            goodWords.add(prefix);
        }
        //Check if Neighbors Valid If yes Recurse through them
        if(checkValid(nodes, x-1,y)){
            DFS(nodes, x-1,y,root,prefix,goodWords);
        }
        if(checkValid(nodes, x+1,y)){
            DFS(nodes, x+1,y,root,prefix,goodWords);
        }
        if(checkValid(nodes, x,y+1)){
            DFS(nodes, x,y+1,root,prefix,goodWords);
        }
        if(checkValid(nodes, x,y-1)){
            DFS(nodes, x,y-1,root,prefix,goodWords);
        }
        //Unmark
        nodes[x][y].setVisited(false);
    }
    // Based on Code used in SpellCheck
    public static void setTrie(String[] dictionary, Trie root){
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

    // Checks if Prefix is a Word
    public static boolean checkTrie(String s, Trie root){
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

    // Checks if Prefix is in Dictionary Trie
    public static boolean checkPre(String prefix, Trie root){
        char c;
        int character;
        for(int i = 0; i < prefix.length(); i++){
            character = prefix.charAt(i) - 'a';
            if(root.getTrie()[character] == null){
                return false;
            }
            root = root.getTrie()[character];
        }
        return true;
    }
}
