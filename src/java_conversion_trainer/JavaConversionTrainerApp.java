package java_conversion_trainer;

class JavaConversionTrainerApp {
	public static void main(String [] args)
	{
		run();
	}
	
	public static void run()
	{
		java.util.Scanner kb = new java.util.Scanner(System.in);
		java.util.Random rand = new java.util.Random();
		
		String [] types = {"byte", "short", "int", "long", "float", "double", "char", "boolean"};
		
		System.out.println("0 girerek çıkabilirsiniz.");
		while (true) {
			String src = types[rand.nextInt(types.length)];
			String dst;
			
			do {
				dst = types[rand.nextInt(types.length)];
			} while (src.equals(dst));
			
			askQuestion(src, dst);
			int answer = Integer.parseInt(kb.nextLine());
			if (answer == 0)
				break;
			boolean isCorrect = isCorrectAnswer(src, dst, answer);
			displayResult(isCorrect);
			if (!isCorrect)
				displayCorrectAnswer(src, dst);
			System.out.println();
		}
	}
	
	public static void askQuestion(String src, String dst)
	{
		System.out.printf("'%s' türünden '%s' türüne doğrudan dönüşüm var mıdır?%n", src, dst);
		System.out.println("1) Evet");
		System.out.println("2) Hayır");
	}
	
	public static boolean isCorrectAnswer(String src, String dst, int answer)
	{
		boolean isValid = TypeInfo.isValidImplicitConversion(src, dst);
		return isValid && answer == 1 || !isValid && answer == 2;
	}
	
	public static void displayResult(boolean isCorrect)
	{
		System.out.printf("%s%n", isCorrect ? "Tebrikler! Doğru cevap." : "Bir dahaki sefere...");
	}
	
	public static void displayCorrectAnswer(String src, String dst)
	{
		System.out.printf("Doğru cevap: '%s'%n", TypeInfo.isValidImplicitConversion(src, dst) ? "Evet" : "Hayır");
	}
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