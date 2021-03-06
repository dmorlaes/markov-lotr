import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class EfficientWordMarkov extends BaseWordMarkov{
    private Map<WordGram, ArrayList<String>> myMap;

    public EfficientWordMarkov(){
        this(3);
    }

    public EfficientWordMarkov(int order) {
        super(order);
        myMap = new HashMap<>();
    }

    @Override
    public void setTraining(String text) {
        super.setTraining(text);
        myMap.clear();
        int index = 0;
        myWords = text.split("\\s+");
        while (index + myOrder - 1 < myWords.length ) {
            WordGram nGram = new WordGram(myWords, index, myOrder);
            ArrayList<String> mapList = new ArrayList<>();
            myMap.putIfAbsent(nGram, mapList);
            if(myWords.length == myOrder + index) {
                myMap.get(nGram).add(PSEUDO_EOS);
            }
            else {
                myMap.get(nGram).add(myWords[index + myOrder]);
            }

            index++;

        }
    }
    @Override
    public ArrayList<String> getFollows(WordGram oGram) {
        if(! (myMap.containsKey(oGram))) {
            throw new NoSuchElementException(oGram.toString() + " not in map");
        }
        return myMap.get(oGram);


    }
}
