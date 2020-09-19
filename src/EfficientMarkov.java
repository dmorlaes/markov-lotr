import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}

	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear();
		int index = 0;
		while (index + myOrder < myText.length()) {
			String s = myText.substring(index, index + myOrder);
			ArrayList<String> list = new ArrayList<>();
			myMap.putIfAbsent(s, list);
			myMap.get(s).add(myText.substring(index + myOrder, index + myOrder + 1));
			index++;

		}
	}
		@Override
	public ArrayList<String> getFollows(String key) {
			if(! (myMap.containsKey(key))) {
				throw new NoSuchElementException(key + " not in map");
			}
			return myMap.get(key);


		}
	}

