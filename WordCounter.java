import java.util.*;
import java.io.*;
import java.lang.*;

/**
 * The main program  opens a text file, counting
 * all of the words it contains and produces one of 
 * three types of output.
 * @author Emma Qin
*/
public class WordCounter{
    
    public static void main(String args[]){
        WordCountMap wordCM = new WordCountMap();
        String inputFilePath = args[1];
        File inputFile = new File(inputFilePath);
        Scanner scanner = null;
        try{
            scanner = new Scanner(inputFile);
            while(scanner.hasNext()){
                //Remove any character in a string that is not a letter.
                String addWord = scanner.next().replaceAll("[^a-zA-Z]","");
                wordCM.incrementCount(addWord);
            }
            scanner.close();
        }catch (FileNotFoundException e){
            System.err.println("File not exist");
            System.exit(0);
        }
        if(args[0].equals("alphabetical")){
            //Print out a list of words and occurence counts one word per line.
            //Each line consits of a word, a colon, and the word's count.
            //The list is sorted alphabetically.
            ArrayList<WordCount> alphabeticList = wordCM.getWordCountsByWord();
            for(int i = 0; i < alphabeticList.size(); i++){
                System.out.println(alphabeticList.get(i).getWord() + ": " + alphabeticList.get(i).getCount());
            }
        }
        if(args[0].equals("frequency")){
            //The list is sorted in decreasing order by counts.
            ArrayList<WordCount> countList = wordCM.getWordCountsByCount();
            for(int i = 0; i < countList.size(); i++){
                System.out.println(countList.get(i).getWord() + ": " + countList.get(i).getCount());
            }
        }
        
        if(args[0].equals("cloud")){
            //Print HTML to the screen containing a word cloud with given 
            //number of words that have highest frequency among all.
            int numWords = Integer.parseInt(args[2]);
            ArrayList<WordCount> fullCountList = wordCM.getWordCountsByCount();
            ArrayList<WordCount> countList = new ArrayList<WordCount>(numWords);
            for(int i = 0; i < numWords; i++){
                countList.add(fullCountList.get(i));
            }
            WordCloudMaker wordCloud = new WordCloudMaker();
            String html = wordCloud.getWordCloudHTML(inputFilePath, countList);
            System.out.println(html);
            
        }
        
    }
}//End of Class