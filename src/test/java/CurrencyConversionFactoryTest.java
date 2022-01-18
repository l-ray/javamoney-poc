import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import javax.money.MonetaryAmount;
import javax.money.convert.ConversionQueryBuilder;
import javax.money.convert.CurrencyConversion;
import javax.money.convert.MonetaryConversions;

import org.javamoney.moneta.Money;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

class CurrencyConversionFactoryTest {

	@Test
	void selectsFromECBWithGivenDate() {
		MonetaryAmount inEUR = Money.of(BigDecimal.TEN, "EUR");

		CurrencyConversion conv2 = MonetaryConversions.getConversion(ConversionQueryBuilder.of()
				.setProviderName("ECB-HIST")
				.setTermCurrency("USD")
				.set(LocalDate.now())
				.build());

		CurrencyConversion conv1 = MonetaryConversions.getConversion(
				ConversionQueryBuilder.of()
						.setProviderName("ECB-HIST")
						.setTermCurrency("USD")
						.set(LocalDate.of(2008, 1, 1))
						.build()
		);

		assertEquals(inEUR.with(conv1), inEUR.with(conv1));
		assertEquals(inEUR.with(conv2), inEUR.with(conv2));
		assertNotEquals(inEUR.with(conv1), inEUR.with(conv2));
	}
}