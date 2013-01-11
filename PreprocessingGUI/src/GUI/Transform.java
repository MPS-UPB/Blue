package GUI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Parser.ComplexType;
import Parser.ElementType;
import Parser.SimpleType;

public class Transform {

	public ArrayList<SimpleType> simpleTypeList;
	public ArrayList<ComplexType> complexTypeList;
	public ArrayList<ElementType> elementsList;

	public Map<String, String> parameters = new HashMap<String, String>();
	public String execDescription = "";
	public String execName = "";
	String input, output;

	public Transform(ArrayList<SimpleType> simpleTypeList,
			ArrayList<ComplexType> complexTypeList,
			ArrayList<ElementType> elementsList, String input, String output) {
		this.simpleTypeList = simpleTypeList;
		this.complexTypeList = complexTypeList;
		this.elementsList = elementsList;
		this.input = input;
		this.output = output;
	}

	public void getExecDescription() {

		for (int i = 0; i < simpleTypeList.size(); i++)
			if (simpleTypeList.get(i).getTypeName().equals("execDescription")) {
				execDescription = simpleTypeList.get(i).getPatternValue();
				break;
			}
	}

	public void getExecName() {

		for (int i = 0; i < simpleTypeList.size(); i++)
			if (simpleTypeList.get(i).getTypeName().equals("execName")) {
				execName = simpleTypeList.get(i).getPatternValue();
				break;
			}

		if (execName.contains(".exe"))
			execName = execName.replace(".exe", "");
	}

	public void getParameters() {
		boolean complexType = false;

		for (int i = 0; i < elementsList.size(); i++)
			if (elementsList.get(i).getParentElementName().equals("task")
					&& !elementsList.get(i).getElementName().equals("execInfo")) {
				String key = elementsList.get(i).getElementName();
				String value = elementsList.get(i).getElementType();
				for (int j = 0; j < complexTypeList.size(); j++)
					if (complexTypeList.get(j).getComplexTypeName()
							.equals(value)) {
						value = complexTypeList.get(j).getAttributeType();
						complexType = true;
						break;
					}
				if (!complexType)
					for (int j = 0; j < simpleTypeList.size(); j++)
						if (simpleTypeList.get(j).getTypeName().equals(value)) {
							value = simpleTypeList.get(j)
									.getPatternRestriction();
							break;
						}
				parameters.put(key, value);
			}
	}

	public void printParameters() {
		for (Map.Entry<String, String> entry : parameters.entrySet())
			System.out.println("Parametru = " + entry.getKey() + ", Tip = "
					+ entry.getValue());
	}

	public String getValue(String key) {

		for (Map.Entry<String, String> entry : parameters.entrySet()) {
			if (entry.getKey().equals(key))
				return entry.getValue();
		}
		return null;
	}

	public void printExecInfo() {
		System.out.println("Numele operatiei este: " + execName);
		System.out.println("Descrierea operatiei este: " + execDescription);
	}

	public Window createWindow() {
		getExecName();
		getExecDescription();
		getParameters();
		Window fereastra = new Window(execName, execDescription, parameters,
				input, output);
		return fereastra;
	}
}
