package frontend.src.main.java.atm.frontend.views;

import backend.entity.MoneyBoxItem;
import backend.enums.CurrencyType;
import business.src.main.java.atm.business.statusview.StatusViewController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Route(value = StatusView.VIEW_ID)
@PageTitle("Status")
public class StatusView extends VerticalLayout {

    public static final String VIEW_ID = "statusview";
    private static final Map<String, String> GRID_OVERVIEW_CAPTIONS = new HashMap<>();
    private StatusViewController controller = new StatusViewController();

    static {
        GRID_OVERVIEW_CAPTIONS.put("value", "Wert");
        GRID_OVERVIEW_CAPTIONS.put("currency", "Währung");
        GRID_OVERVIEW_CAPTIONS.put("amount", "Bestand");
        GRID_OVERVIEW_CAPTIONS.put("type", "Geiler Typ");
    }

    public StatusView() {
        addClassName("statusView");
        add(label());
        add(statusMenuButtons());
        add(moneyGrid());
    }

    private VerticalLayout statusMenuButtons() {
        VerticalLayout statusLayout = new VerticalLayout();
        statusLayout.addClassName("statusMenuButtons");
        Button backButton = new Button("Zurück");
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(""));

        statusLayout.add(backButton);
        return statusLayout;
    }

    private Label label() {
        Label statusLable = new Label("Status");
        statusLable.addClassName("label");
        return statusLable;
    }

    private Grid moneyGrid() {
        List<MoneyBoxItem> moneyBoxes = controller.getMoneyBoxItems();
        Grid<MoneyBoxItem> moneyGrid = new Grid<>(MoneyBoxItem.class);


        moneyGrid.setItems(moneyBoxes);
        moneyGrid.removeColumnByKey("type");
        moneyGrid.addComponentColumn(i -> {
            if(CurrencyType.COIN.equals(i.getType())) {
                return VaadinIcon.PIGGY_BANK_COIN.create();
            }

           return VaadinIcon.MONEY.create();
        }).setKey("type");

        GridCaptionHelper.setGridCaptions(moneyGrid.getColumns(), GRID_OVERVIEW_CAPTIONS);

        add(moneyGrid);

        return moneyGrid;
    }

}
