package business.src.main.java.atm.business.dtos;

import backend.entity.MoneyBoxItem;
import backend.enums.Currency;
import backend.enums.CurrencyType;
import com.vaadin.flow.component.icon.Icon;

/**
 * @deprecated only for showcase
 */
@Deprecated
public class MoneyBoxItemDTO implements MoneyBoxItem {

    private int value;
    private Currency currency;
    private CurrencyType type;
    private int amount = 0;
    private Icon icon;

    @Override
    public int getValue() {
        return value;
    }

    @Override
    public Currency getCurrency() {
        return currency;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
    public CurrencyType getType() {
        return CurrencyType.COIN;
    }

    public Icon getIcon() {
        return icon;
    }


    public void setValue(int value) {
        this.value = value;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }
}
