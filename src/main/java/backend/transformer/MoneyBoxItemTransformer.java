package backend.transformer;

import backend.entity.MoneyBox;
import backend.entity.MoneyBoxItem;
import backend.enums.CurrencyType;
import business.src.main.java.atm.business.dtos.MoneyBoxItemDTO;
import com.vaadin.flow.component.icon.VaadinIcon;

import java.util.ArrayList;
import java.util.List;

/**
 * @deprecated Only as an example
 */
@Deprecated
public class MoneyBoxItemTransformer {

    public static List<MoneyBoxItem> transform(List<MoneyBox> boxes) {
        List<MoneyBoxItem> results = new ArrayList<>(boxes.size());
        for (MoneyBox box : boxes) {
            results.add(transform(box));
        }
        return results;
    }

    public static MoneyBoxItem transform(MoneyBox box) {

        MoneyBoxItemDTO dto = new MoneyBoxItemDTO();

        dto.setAmount(box.getAmount());
        dto.setCurrency(box.getCurrency());
        dto.setValue(box.getValue());

        if (CurrencyType.NOTE.equals(box.getType())) {
            dto.setIcon(VaadinIcon.MONEY.create());
        } else {
            dto.setIcon(VaadinIcon.PIGGY_BANK_COIN.create());
        }

        return dto;
    }

}
