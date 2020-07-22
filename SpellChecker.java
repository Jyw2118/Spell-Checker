//Joanne Wang

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;


public class SpellChecker implements SpellCheckerInterface 
{
    private HashSet<String> dictionary;
    
    public SpellChecker(String fileName) throws IOException {
        BufferedReader br= new BufferedReader(new FileReader(fileName));
        
        dictionary = new HashSet<>();
        String st; 
        
        while ((st=br.readLine())!= null){
            st=st.toLowerCase();
            dictionary.add(st);
        }
        br.close();
    }
    
	public List<String> getIncorrectWords(String filename) {   
        ArrayList<String> incorrect = new ArrayList<String>();
        
        try{
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        while ((line = br.readLine()) != null) {
            line= line.toLowerCase();
            String[] words= line.split("\\s+");
            if(line.equals(""))
                continue;
            for (String word: words){
                word = word.replaceAll("[^a-zA-Z0-9]", "");
                if (!dictionary.contains(word))
                    incorrect.add(word);
            }
        }
            
        br.close();
        return incorrect;
        }
        catch(IOException e){
            System.out.println("IOException Error");
            return incorrect;
        }
        
    }
    
	public Set<String> getSuggestions(String word){
        Set<String> suggestedWords= new HashSet<>();
        
        //insert letter
        for (char c='a'; c<='z';c++){
            for (int i=0; i<=word.length();i++){
                String newWord= word.substring(0,i)+c+word.substring(i,word.length());
                if(dictionary.contains(newWord))
                    suggestedWords.add(newWord);
            }
        }
        
        //remove character
        for (int i=0; i<word.length();i++){
            String newWord= word.substring(0,i)+word.substring(i+1,word.length());
            if(dictionary.contains(newWord))
                suggestedWords.add(newWord);
        }
        
        //swap adjacent characters
        for (int i=0; i<word.length()-1;i++){
            char[] wordChar= word.toCharArray();
            char temp=wordChar[i];
            wordChar[i]=wordChar[i+1];
            wordChar[i+1]=temp;
            String newWord= new String(wordChar);
            if(dictionary.contains(newWord))    
                suggestedWords.add(newWord);
        }
        
        return suggestedWords;
    }
}