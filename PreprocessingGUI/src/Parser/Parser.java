package Parser;
import com.sun.xml.xsom.impl.parser.DelayedRef;
import com.sun.xml.xsom.parser.XSOMParser;
import com.sun.xml.xsom.XSAttributeDecl;
import com.sun.xml.xsom.XSAttributeUse;
import com.sun.xml.xsom.XSFacet;
import com.sun.xml.xsom.XSRestrictionSimpleType;
import com.sun.xml.xsom.XSSchemaSet;
import com.sun.xml.xsom.XSComplexType;
import com.sun.xml.xsom.XSContentType;
import com.sun.xml.xsom.XSElementDecl;
import com.sun.xml.xsom.XSModelGroup;
import com.sun.xml.xsom.XSParticle;
import com.sun.xml.xsom.XSSchema;
import com.sun.xml.xsom.XSSimpleType;
import com.sun.xml.xsom.XSTerm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.io.File;
import java.lang.reflect.Field;

public class Parser {

	public void parseSchema()
	{  
		File file = new File("D:\\anul IV (2012 - 2013)\\sem I\\MPS\\Proiect\\ProjMps\\otsu.xsd");

		SimpleType simpleType;
		ComplexType complexType;
		XSSimpleType  simple = null;
		XSComplexType complex = null;
		ArrayList<SimpleType> simpleTypeList = new ArrayList<SimpleType>();
		ArrayList<ComplexType> complexTypeList = new ArrayList<ComplexType>();

		try {
			XSOMParser parser = new XSOMParser();
			parser.parse(file);

			XSSchemaSet sset = parser.getResult();
			XSSchema mys = sset.getSchema(1);
			XSSchema s = (XSSchema)sset.iterateSchema().next();
			XSSchema globalSchema = sset.getSchema("");

			System.out.println("Target namespace: "+s.getTargetNamespace());

			//---------------------------------------------------------------------------------------Complex Type stuff			

			Iterator<XSComplexType> complexTypeItr = globalSchema.iterateComplexTypes();
			while (complexTypeItr.hasNext()){
				complex = (XSComplexType) complexTypeItr.next();
				complexType = getComplexType(complex);
				complexTypeList.add(complexType);
			}

			System.out.println("\nAfisare Complex Types:");
			for(int i=0;i<complexTypeList.size();i++)
				System.out.println(complexTypeList.get(i).getComplexTypeName() + " -- " + complexTypeList.get(i).getAttributeName() + " -- " + complexTypeList.get(i).getAttributeType());

			// ---------------------------------------------------------------------------------------Simple Type stuff

			Iterator<XSSimpleType> simpleTypeItr = mys.iterateSimpleTypes();
			while (simpleTypeItr.hasNext()){
				simple = (XSSimpleType) simpleTypeItr.next();
				simpleType = getSimpleType(simple);
				simpleTypeList.add(simpleType);
			}
			System.out.println("\nAfisare Simple Types:");
			for(int i=0;i<simpleTypeList.size();i++)
				System.out.println(simpleTypeList.get(i).getTypeName() + " -- " + simpleTypeList.get(i).getPatternRestriction() + " -- " + simpleTypeList.get(i).getPatternValue());

			// -------------------------------------------------------------------------------------------			

			System.out.println();
			if (globalSchema.getElementDecls().size() != 1)
			{
				throw new Exception("Should be only elment type per file.");
			}

			//---------------------------------------------------------------------------------------------- get Elements and what is inside

			XSElementDecl ed = globalSchema.getElementDecls().values().toArray(new XSElementDecl[1])[0];
			ElementGroup elementGroup = new ElementGroup();
			ArrayList<ElementType> elementsList = new ArrayList<ElementType>();
			elementGroup.setGlobalName(ed.getName());
			XSContentType xsContentType = ed.getType().asComplexType().getContentType();
			XSParticle particle = xsContentType.asParticle();
			String parentElementName = null;
			if (particle != null)
			{
				XSTerm term = particle.getTerm();
				if (term.isModelGroup())
				{
					XSModelGroup xsModelGroup = term.asModelGroup();
					XSParticle[] particles = xsModelGroup.getChildren();
					XSParticle pp =particles[0];
					String elemName = pp.getTerm().asElementDecl().getName();

					for (XSParticle p : particles)
					{
						XSTerm pterm = p.getTerm();
						if (pterm.isElementDecl())
						{           
							XSElementDecl ee = (XSElementDecl)pterm; 
							parentElementName = ee.getName();

							ElementType elementType = new ElementType(ee.getName(), getElementType(ee), ed.getName());
							elementsList.add(elementType);

							if (pterm.asElementDecl().getName().equals(elemName)){
								XSContentType content = ee.getType().asComplexType().getContentType();
								XSParticle particle1 = content.asParticle();
								getInternElements(particle1, elementsList, parentElementName);
							}
						}    
					}
				}
			}
			System.out.println("\nAfisare Element Types:");
			elementGroup.setElementsList(elementsList);
			for(int i=0; i<elementsList.size(); i++)
				System.out.println(elementsList.get(i).getParentElementName() + " -- " +elementsList.get(i).getElementName() + " -- " +elementsList.get(i).getElementType());
			System.out.println("\nAfisare parametrii:");
			for(int i=1; i<elementsList.size(); i++){
				if(elementsList.get(i).getParentElementName().equals(elementGroup.getGlobalName()))
					System.out.println(elementsList.get(i).getParentElementName() + " -- " +elementsList.get(i).getElementName() + " -- " +elementsList.get(i).getElementType());
			}
			
			Transform trans = new Transform(simpleTypeList, complexTypeList, elementsList);
			System.out.println("\n************************************************\n");
			trans.createWindow();
		}
		catch (Exception exp) {
			exp.printStackTrace(System.out);
		}
	}

	//-------------------------------------------------------------------------------------------------------
	private String getElementType(XSElementDecl ee) {
		try {
			if(ee.getType().getName() == null)
				return ee.getType().getBaseType().getName();
			return ee.getType().getName();

		} catch(InternalError e) {
			if(e.getMessage().equalsIgnoreCase("unresolved reference")) {
				try {
					Field f = ee.getClass().getDeclaredField("type");
					f.setAccessible(true);
					Object fObj = f.get(ee);
					if(fObj instanceof DelayedRef)
						((DelayedRef)fObj).run();
				} catch (Exception e2) {
					throw new RuntimeException(e2);
				}
			}

			if(ee.getType().getName() == null)
				return ee.getType().getBaseType().getName();
			return ee.getType().getName();
		}
	}

	private void getInternElements(XSParticle particle, ArrayList<ElementType> elementsList, String elemName) {
		if (particle != null)
		{
			XSTerm term = particle.getTerm();
			if (term.isModelGroup())
			{
				XSModelGroup xsModelGroup = term.asModelGroup();
				XSParticle[] particles = xsModelGroup.getChildren();
				for (XSParticle p : particles)
				{
					XSTerm pterm = p.getTerm();
					if (pterm.isElementDecl())
					{            
						ElementType elementType = new ElementType(pterm.asElementDecl().getName(), getElementType(pterm.asElementDecl()), elemName);
						elementsList.add(elementType);
					}
				}
			}
		}
	}

	private SimpleType getSimpleType(XSSimpleType simple){

		XSRestrictionSimpleType restriction = simple.asRestriction();
		if(restriction != null){
			Iterator<? extends XSFacet> i = restriction.getDeclaredFacets().iterator();
			SimpleType simpleType;
			if(restriction.getDeclaredFacets().size() == 0){
				simpleType = new SimpleType(restriction.getName(), restriction.getBaseType().getName(), null);
				return simpleType;
			}
			XSFacet facet = i.next();
			simpleType = new SimpleType(restriction.getName(), restriction.getBaseType().getName(), facet.getValue().value);
			return simpleType;
		}
		return null;
	}


	private ComplexType getComplexType(XSComplexType complex){

		Collection<? extends XSAttributeUse> c = complex.getAttributeUses();
		Iterator<? extends XSAttributeUse> i = c.iterator();
		ComplexType complexType;

		XSAttributeDecl attributeDecl = i.next().getDecl();
		complexType = new ComplexType(complex.getName(), attributeDecl.getName(), attributeDecl.getType().getName());
		return complexType;
	}

	public static void main(String [] args){

		Parser rr = new Parser();
		rr.parseSchema();
	}
}

