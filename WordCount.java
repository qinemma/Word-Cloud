/**
 * WordCount class creates an object that contains a word
 * and its associated count.
 * @author Emma Qin
*/
public class WordCount implements Comparable<WordCount>{
    //Instance variables for WordCount
    public String word;
    public int count;
    
    public WordCount(String word, int count){
        this.word = word;
        this.count = count;
    }
    
    /**
     * A string representation of the word.
     *
     * @return String-a string representation of the word.
     */
    public String getWord(){
        return word;
    }
    
    /**
     * An integer representation of the number of counts.
     *
     * @return int-an integer representation of the number of counts.
     */
    public int getCount(){
        return count;
    }
    
    /**
     * A method comparing two WordCounts by counts
     *
     * @return int-an integer representation of the comparision of two counts.
     */
    public int compareTo(WordCount wordCount){
        if(wordCount.getCount() > getCount()){
            return -1;
        }
        else{
            if(wordCount.getCount() == getCount()){
                return 0;
            }
            else{
                return 1;
            }
        }
    }
}//End of Class