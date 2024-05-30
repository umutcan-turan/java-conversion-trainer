package java_conversion_trainer;

class JavaConversionTrainerApp {

}

class TypeInfo {
	public static boolean isValidImplicitConversion(String src, String dst)
	{
		String [] byteDest  = {"short", "int", "long", "float", "double"};
		String [] shortDest = {"int", "long", "float", "double"};
		String [] intDest   = {"long", "float", "double"};
		String [] longDest  = {"float", "double"};
		String [] charDest  = {"int", "long", "float", "double"};
		String [] floatDest = {"double"};
		
		String [][] rules = {byteDest, shortDest, intDest, longDest, charDest, floatDest};
		
		int idx = getIdx(src);
		if (idx == -1)
			return false;
		return ruleContains(rules[idx], dst);
	}
	
	public static int getIdx(String src)
	{
		return switch (src) {
		case "byte"  -> 0;
		case "short" -> 1;
		case "int"   -> 2;
		case "long"  -> 3;
		case "char"  -> 4;
		case "float" -> 5;
		default      -> -1;
		};
	}
	
	public static boolean ruleContains(String [] arr, String key)
	{
		for (int i = 0; i < arr.length; i++)
			if (key.equals(arr[i]))
					return true;
		return false;
	}
}