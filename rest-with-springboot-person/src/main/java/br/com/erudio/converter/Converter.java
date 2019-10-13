package br.com.erudio.converter;

public class Converter {

	public static boolean isNumeric(String strNumber) {
		if(strNumber == null) return false;
		String number = strNumber.replace(",", ".");
		return number.matches("[-+]?[0-9]*\\.?[0-9]+");
	}

	public static Double convertToDouble(String strNumber) {
		String number = strNumber.replace(",", ".");
		if (isNumeric(number)) return Double.parseDouble(number);
		return 0D;
	}
}
