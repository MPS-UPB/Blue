package Parser;


public class SimpleType {

	private String typeName;
	private String patternRestriction;
	private String patternValue;

	public SimpleType(String typeName, String patternRestriction, String patternValue){
		this.typeName = typeName;
		this.patternRestriction = patternRestriction;
		this.patternValue = patternValue;
	}

	public String getTypeName() {

		return typeName;
	}

	public String getPatternRestriction() {

		return patternRestriction;
	}

	public String getPatternValue() {

		return patternValue;
	}
}

