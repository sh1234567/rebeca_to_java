package org.rebecalang.modeltransformer.java;

import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.ArrayType;
import org.rebecalang.compiler.modelcompiler.corerebeca.objectmodel.Type;
import org.rebecalang.compiler.utils.TypesUtilities;

public class Rebeca2JavaTypesUtilities {

	public static String getCorrespondingJavaType(Type rebecaType) {
		// TODO Auto-generated method stub
		if(rebecaType instanceof ArrayType)
			((ArrayType)rebecaType).getDimensions();
		if(rebecaType == TypesUtilities.BOOLEAN_TYPE)
			return "boolean";
		if(rebecaType == TypesUtilities.INT_TYPE)
			return "int";
		if(rebecaType == TypesUtilities.DOUBLE_TYPE)
			return "double";
		if(rebecaType == TypesUtilities.STRING_TYPE)
			return "String";
		if(rebecaType == TypesUtilities.BYTE_TYPE)
			return "byte";
		return TypesUtilities.getTypeName(rebecaType);
	}

}
