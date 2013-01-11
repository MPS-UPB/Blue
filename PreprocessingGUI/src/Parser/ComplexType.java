package Parser;


public class ComplexType {

	private String complexTypeName;
	private String attributeName;
	private String attributeType;

	public ComplexType(String complexTypeName, String attributeName, String attributeType){
		this.complexTypeName = complexTypeName;
		this.attributeName = attributeName;
		this.attributeType = attributeType;
	}

	public String getComplexTypeName() {

		return complexTypeName;
	}

	public String getAttributeName() {

		return attributeName;
	}

	public String getAttributeType() {

		return attributeType;
	}

}
