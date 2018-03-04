package com.example.layering.model.impl.client;

import com.example.layering.model.api.client.Amount;
import com.example.layering.model.api.client.Currency;
import java.math.BigDecimal;

public class AmountImpl implements Amount {
	
	BigDecimal value;
	Currency currency;

	public AmountImpl(BigDecimal value, Currency currency) {
		super();
		this.value = value;
		this.currency = currency;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	@Override
	public BigDecimal getValue() {
		return value;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	@Override
	public Currency getCurrency() {
		return currency;
	}
	
}
