package Parser;

import java.util.ArrayList;


public class ElementGroup {

	public String globalElementName;
	public ArrayList<ElementType> elementsList = new ArrayList<ElementType>();


	public void setElementsList(ArrayList<ElementType> list) {
		elementsList = list;
	}

	public void setGlobalName(String globalN) {
		globalElementName = globalN;
	}


	public String getGlobalName() {

		return globalElementName;
	}

	public ArrayList<ElementType> getElementsList() {

		return elementsList;
	}
}
