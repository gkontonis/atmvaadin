package backend.entity;

import backend.enums.Currency;
import backend.enums.CurrencyType;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.data.renderer.Renderer;

public interface MoneyBoxItem {

    int getValue();

    Currency getCurrency();

    int getAmount();

    CurrencyType getType();
}
