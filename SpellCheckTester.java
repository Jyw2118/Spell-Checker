//Joanne Wang

import java.io.IOException;
public class SpellCheckTester{
    public static void main(String[] args) throws IOException{
        SpellChecker test= new SpellChecker("words.txt");
        System.out.println(test.getIncorrectWords("test.txt"));
        System.out.println(test.getSuggestions("whitene"));
        
        
    }
}