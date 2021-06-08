
import java.util.*;

public class AnagramSolver {
   private List<String> dictionary; // dictionary of words
   private Map<String, LetterInventory> mapDictionary; // letter inventory of dictionary
   
   // post: creates a dictionary from a given list
   // list is non-empty and doesn't contain duplicates
   public AnagramSolver(List<String> list) {
      dictionary = list;
      makeMap();
   }
   
   // pre: if max < 0 throws IllegalArgumentException
   // post: prints all combinations of words that have the same
   // letters as the given word that the user is inputting
   // post: takes in a max number and prints out all of the combinations
   // that includes at most max words
   public void print(String s, int max) {
      if(max < 0) {
         throw new IllegalArgumentException();
      }
      LetterInventory word = new LetterInventory(s);
      Stack<String> wordsToKeep = new Stack<String>();
      printHelper(dictionary, word, max, wordsToKeep);
      
   }
   
   // post: returns a count of alphabetical letters in a given
   // word entered in by the user ignoring the case of letters and ignoring
   // any non-alphabetic characters
   private LetterInventory getWord(String s) {
      LetterInventory originalWord = new LetterInventory(s);
      return originalWord;
   }
   
   // post: creates a map of a word and its count of alphabetical letters
   // in the given word from the dictionary
   private void makeMap() {
      mapDictionary = new HashMap<String, LetterInventory>();
      for(String word: dictionary) {
         LetterInventory letterm = new LetterInventory(word);
         if(mapDictionary.containsKey(word)){
            mapDictionary.get(word).add(letterm);
         } else {
             
            mapDictionary.put(word, letterm);
         }
      }
   }
   
   // post: searches dictionary for "relevant" words that can be subtracted from a 
   // given phrase and builds up a list of all of the words that can be created from 
   // the word entered in by the user
   // post: continues this search until the word entered by the user is completly empty
   // post: if a word is null it means that the phrase the user inputted was not able to be 
   // subtracted from the word from the dictionary 
   private void printHelper(List<String> dictionary, LetterInventory 
                           originalWord, int max, Stack<String> wordsToKeep) {     
      if(originalWord.isEmpty() && max == 0) {
         System.out.println(wordsToKeep);
      } else if (originalWord.isEmpty() && wordsToKeep.size() <= max) {
         System.out.println(wordsToKeep);
      } else {
         for(String str: dictionary) {
           
            LetterInventory word = mapDictionary.get(str);
            LetterInventory leftOver = originalWord.subtract(word);
            if(leftOver != null){
               wordsToKeep.push(str);
               printHelper(dictionary, leftOver, max, wordsToKeep);
               wordsToKeep.pop();
            }
         }
                        
      }
         
      
   } 
   
}