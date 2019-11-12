package com.virtusa.java;

import java.text.DecimalFormat;

public class NumberToWordConvertor {

	private static final String[] tensDigitNames = { "", " ten", " twenty", " thirty", " forty", " fifty", " sixty",
			" seventy", " eighty", " ninety" };

	private static final String[] unitDigitNames = { "", " one", " two", " three", " four", " five", " six", " seven",
			" eight", " nine", " ten", " eleven", " twelve", " thirteen", " fourteen", " fifteen", " sixteen",
			" seventeen", " eighteen", " nineteen" };

	private static String convertLessThanOneThousand(int number) {
		String result;

		if (number % 100 < 20) {
			result = unitDigitNames[number % 100];
			number /= 100;
		} else {
			result = unitDigitNames[number % 10];
			number /= 10;

			result = tensDigitNames[number % 10] + result;
			number /= 10;
		}
		if (number == 0)
			return result;
		return unitDigitNames[number] + " hundred" + result;
	}

	public static String convert(long number) {
		// 0 to 999 999 999 999
		if (number == 0) {
			return "zero";
		}

		String svalue = Long.toString(number);

		// pad with "0"
		String mask = "000000000000";
		DecimalFormat df = new DecimalFormat(mask);
		svalue = df.format(number);

		int billions = Integer.parseInt(svalue.substring(0, 3));

		int millions = Integer.parseInt(svalue.substring(3, 6));

		int hundredThousands = Integer.parseInt(svalue.substring(6, 9));

		int thousands = Integer.parseInt(svalue.substring(9, 12));

		String tradBillions;
		switch (billions) {
		case 0:
			tradBillions = "";
			break;
		case 1:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
			break;
		default:
			tradBillions = convertLessThanOneThousand(billions) + " billion ";
		}
		String result = tradBillions;

		String tradMillions;
		switch (millions) {
		case 0:
			tradMillions = "";
			break;
		case 1:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
			break;
		default:
			tradMillions = convertLessThanOneThousand(millions) + " million ";
		}
		result = result + tradMillions;

		String tradHundredThousands;
		switch (hundredThousands) {
		case 0:
			tradHundredThousands = "";
			break;
		case 1:
			tradHundredThousands = "one thousand ";
			break;
		default:
			tradHundredThousands = convertLessThanOneThousand(hundredThousands) + " thousand ";
		}
		result = result + tradHundredThousands;

		String tradThousand;
		tradThousand = convertLessThanOneThousand(thousands);
		result = result + tradThousand;

		// To remove any extra spaces...
		return result.replaceAll("^\\s+", "").replaceAll("\\b\\s{2,}\\b", " ");
	}

	/**
	 * 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(1));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(21));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(105));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(56945781));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(0));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(1316));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(1000000));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(9000000));
		System.out.println("Equivalent Number in English Words: " + NumberToWordConvertor.convert(123456789));

		/* Below is the console output for above statements */
//		Equivalent Number in English Words: one
//		Equivalent Number in English Words: twenty one
//		Equivalent Number in English Words: one hundred five
//		Equivalent Number in English Words: fifty six million nine hundred forty five thousand seven hundred eighty one
//		Equivalent Number in English Words: zero
//		Equivalent Number in English Words: one thousand three hundred sixteen
//		Equivalent Number in English Words: one million 
//		Equivalent Number in English Words: nine million 
//		Equivalent Number in English Words: one hundred twenty three million four hundred fifty six thousand seven hundred eighty nine

	}
}