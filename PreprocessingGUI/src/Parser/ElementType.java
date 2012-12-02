package Parser;

public class ElementType {

	private String elementName;
	private String elementType;
	private String parentElementName;

	public ElementType(String elemN, String elemT, String parentElemN){
		elementName = elemN;
		elementType = elemT;
		parentElementName = parentElemN;
	}

	public String getElementName() {

		return elementName;
	}

	public String getElementType() {

		return elementType;
	}

	public String getParentElementName() {

		return parentElementName;
	}
}
