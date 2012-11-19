package GUI;

/**
 * Transform - clasa care memoreaza datele unei transformari.
 * 
 * @author Valentina Bold
 * @version 1.0, 19 Nov 2012
 */

import java.util.HashMap;
import java.util.Map;

public class Transform {

	public Map<String, String> parameters = new HashMap<String, String>();
	public String name_operation;

	public Transform() {

	}

	public Transform(String name_op) {
		this.name_operation = name_op;
	}

	public void setParameter(String parameter, String value) {
		parameters.put(parameter, value);
	}

	public String getValue(String parameter) {
		if (parameters.containsKey(parameter))
			return parameters.get(parameter);

		return null;
	}

	public void print() {
		System.out.println("Numele operatiei efectuate este " + name_operation);
		for (Map.Entry<String, String> entry : parameters.entrySet())
			System.out.println("Key = " + entry.getKey() + ", Value = "
					+ entry.getValue());
	}
}
