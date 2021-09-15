import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@DisplayNameGeneration(ReplaceUnderscores.class)
class RomanConverterTest {

	private RomanConverter converter;

	@BeforeEach
	void before() {
		converter = new RomanConverter();
	}

	@ParameterizedTest(name = "should convert {0} to {1}")
	@CsvSource(value = {
			"13 => XIII",
			"14 => XIV",
			"18 => XVIII",
			"26 => XXVI",
			"39 => XXXIX",
			"42 => XLII",
			"61 => LXI",
			"545 => DXLV",
			"217 => CCXVII" }, delimiterString = " => ")
	void should_convert_number_to_roman_numeral(Integer input, String expected) {
		assertThat(converter.convert(input)).isEqualTo(expected);
	}

	@Test
	void should_return_empty_when_number_is_0() {
		assertThat(converter.convert(0)).isEmpty();
	}

	@ParameterizedTest(name = "should convert {0} to {1}")
	@CsvSource(value = {
			"VII => 7",
			"XXXIV => 34",
			"XXXVII => 37",
			"XLIII => 43",
			"XLVIII => 48",
			"LII => 52",
			"CXL => 140",
			"CXLV => 145" }, delimiterString = " => ")
	void should_convert_roman_numeral_to_number(String input, Integer expected) {
		assertThat(converter.convert(input)).isEqualTo(expected);
	}

	@Test
	void should_return_0_when_is_empty() {
		assertThat(converter.convert("")).isEqualTo(0);
	}

	@Test
	void should_return_0_when_is_null() {
		assertThat(converter.convert(null)).isEqualTo(0);
	}

	@Test
	void should_not_convert_to_number_when_is_not_a_roman_numeral() {
		assertThatThrownBy(() -> converter.convert("plop"))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("Cannot be converted")
				.hasNoCause();
	}

}
