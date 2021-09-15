import static java.util.Arrays.stream;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.util.List;

public class RomanConverter {

	public String convert(int number) {
		if (number <= 0) {
			return "";
		}
		var result = new StringBuilder();
		var remainder = number;
		for (RomanNumeral romanNumeral : RomanNumeral.descendingValues()) {
			while (remainder >= romanNumeral.getValue()) {
				result.append(romanNumeral.name());
				remainder -= romanNumeral.getValue();
			}
		}
		return result.toString();
	}

	public int convert(String roman) {
		if (roman == null || roman.isBlank()) {
			return 0;
		}
		var result = 0;
		var remainder = roman.toUpperCase().trim();
		checkIsRomanNumeral(remainder);
		while (remainder.length() > 0) {
			for (RomanNumeral romanNumeral : RomanNumeral.descendingValues()) {
				if (remainder.startsWith(romanNumeral.name())) {
					result += romanNumeral.getValue();
					remainder = remainder.substring(romanNumeral.name().length());
				}
			}
		}
		return result;
	}

	private void checkIsRomanNumeral(String roman) {
		if (stream(roman.split("")).anyMatch(c -> !RomanNumeral.contains(c))) {
			throw new IllegalArgumentException("Cannot be converted");
		}
	}

	public enum RomanNumeral {
		I(1),
		IV(4),
		V(5),
		IX(9),
		X(10),
		XL(40),
		L(50),
		XC(90),
		C(100),
		CD(400),
		D(500);

		private int value;

		private RomanNumeral(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public static List<RomanNumeral> descendingValues() {
			return stream(RomanNumeral.values())
					.sorted(comparing(RomanNumeral::getValue).reversed())
					.collect(toList());
		}

		public static boolean contains(String value) {
			return stream(RomanNumeral.values())
					.map(RomanNumeral::name)
					.collect(toList())
					.contains(value);
		}

	}
}
