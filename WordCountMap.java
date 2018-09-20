import java.util.*;

/**
 * This class contains objects that each represents a binary search tree.
 * The tree is organized according to the alphabetical ordering of the words.
 * @author Emma Qin
*/

public class WordCountMap{
    //Instance variables for WordCountMap
    private Node root;
    private int numNodes;
    
    private class Node{
        //Private Node class that is used to construct a 
        //Binary Search Tree
        public String word;
        public int count;
        public Node left;
        public Node right;
        
        private Node(String word){
            WordCount wordNode = new WordCount(word, 1);
            this.word = word;
            this.count = 1;
            this.left = null;
            this.right = null;
        }
        
        private WordCount getWordCount(){
            WordCount cur = new WordCount(word, count);
            return cur;
        }
    }//End Node class
    

    /**
     * Creates an empty WordCountMap
     */
    public WordCountMap(){
        root = null;
        numNodes = 0;
    }

    
    /**
     * If the specific word is already in this WordCountMap, then its 
     * count is increased by one. Otherwise, the word is added to this map 
     * with a count of 1.
     *
     * @param String word
     */
    public void incrementCount(String word){
        word = word.toLowerCase();
        //Words in StopWords.txt are not added to the map
        Scanner scan = new Scanner("StopWords.txt");
        HashSet<String> stopString = new HashSet<>(Arrays.asList(new String[] {"the", "of", "and", "to", "i", "a", "that", "it", "in", "he", "you", "was", "his", "is", "have", "had", "with", "my", "we", "for", "which", "as", "but", "not", "this", "at", "be", "me", "him", "there", "from", "on", "so", "by", "if", "been", "are", "our", "were", "an", "what", "or", "could", "would", "do", "will", "your", "us", "has", "her", "then", "who", "when", "should", "some", "more", "she", "any", "may", "did", "into", "can", "they", "how", "all", "them", "said", "their", "one", "went", "other", "am", "such", "than", "himself", "herself", "without", "these", "where", "about"}));
        if(!stopString.contains(word)){
            Node cur = new Node(word);
            if(root == null){
                root = cur;
                numNodes++;
            }
            else{
                incrementCountHelper(word,root);
            }   
        } 
    }

    /**
     * Helper method that recursively search and update the counts.
     *
     * @param String word
     * @param Node temp
     */
    public void incrementCountHelper(String word, Node temp){
        Node addedNode = new Node(word);
        if(word.equals(temp.word)){
            temp.count++;
        }
        else{
            if(word.compareTo(temp.word) > 0){
                if(temp.right == null){
                    temp.right = addedNode;
                    numNodes++;
                }
                else{
                    incrementCountHelper(word, temp.right);
                }
            }
            else{
                if(temp.left == null){
                    temp.left = addedNode;
                    numNodes++;
                }
                else{
                    incrementCountHelper(word,temp.left);
                }
            }
        }
    }


    /**
     * Using in-order traversal to add the words into an array list.
     * So the list is sorted alphabetically naturally.
     *
     * @param Node node
     * @param ArrayList<WordCount> arr
     */
    public void traversal(Node node, ArrayList<WordCount> arr){
        if(node == null){
            return;
        }
        traversal(node.left, arr);
        arr.add(node.getWordCount());
        traversal(node.right, arr);
    }

    /**
     * Using inerstion sort to sort the word counts in decreasing order
     * by counts.
     *
     * @param ArrayList<WordCount> arr
     * @param int first
     * @param int last
     */
    public void insertionsort(ArrayList<WordCount> arr, int first, int last){
        for(int i = first+1; i <= last; i++) {
            int j = i;
            while(j > first && arr.get(j-1).count < arr.get(j).count) {
                WordCount nodeToSwap = arr.get(j);
                arr.set(j, arr.get(j-1));
                arr.set(j-1, nodeToSwap);
                j--;
            }
        }
    }
    
    /**
    * Returns an array list of WordCount objects, one per WordCount 
    * stored in this WordCountMap, sorted in decreasing order by count.
    *
    * @return ArrayList<WordCount> an array list of WordCount objects. 
    */
    public ArrayList<WordCount> getWordCountsByCount(){
        ArrayList<WordCount> wordListByCount = new ArrayList<WordCount>(numNodes);
        traversal(root, wordListByCount);
        insertionsort(wordListByCount, 0, numNodes - 1);
        return wordListByCount;
    }
    
    
    /**
    * Returns a list of WordCount objects, one per WordCount stored in this
    * WordCountMap, sorted alphabetically by word.
    * 
    * @return ArrayList<WordCount> a list of WordCount objects. 
    */
    public ArrayList<WordCount> getWordCountsByWord(){
        ArrayList<WordCount> wordListByWord = new ArrayList<WordCount>(numNodes); 
        traversal(root,wordListByWord);
        return wordListByWord;
    }
}//End  of Class
    

