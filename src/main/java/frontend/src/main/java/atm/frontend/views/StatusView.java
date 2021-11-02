package frontend.src.main.java.atm.frontend.views;

import backend.calculator.Calculator;
import backend.entity.MoneyBox;
import backend.entity.MoneyBoxContainer;
import backend.enums.Currency;
import business.src.main.java.atm.business.statusview.StatusViewController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Label;

import java.util.List;


@Route(value = StatusView.VIEW_ID)
@PageTitle("Status")
public class StatusView extends VerticalLayout {
    public static final String VIEW_ID = "statusview";
    private StatusViewController controller = new StatusViewController();
    private Calculator CALCULATOR = new Calculator();
    private MoneyBoxContainer moneyBoxContainer = new MoneyBoxContainer(); //G

    public StatusView() {
        addClassName("statusView");
        add(label());
        add(statusMenuButtons());
        add(moneyGrid());
    }

    private VerticalLayout statusMenuButtons() {
        VerticalLayout statusLayout = new VerticalLayout();
        statusLayout.addClassName("statusMenuButtons");

        //TODO OO Text status = new Text(controller.getStatus());

        Button backButton = new Button("Zurück");
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(""));

        //statusLayout.add(status, backButton);
        statusLayout.add(backButton);

        return statusLayout;
    }

    private Label label() {
        Label statusLable = new Label("Status");
        statusLable.addClassName("label");
        return statusLable;
    }


    private Grid moneyGrid() {
        List<MoneyBox> moneyBoxes = null;

        for (Currency currency : CALCULATOR.getContainer().getMap().keySet()) {
            moneyBoxes = CALCULATOR.getContainer().get(currency);
        }

        /*for(Currency c : moneyBoxContainer.getMap().keySet()){
            moneyBoxes = moneyBoxContainer.get(c);
        }*/

        Grid<MoneyBox> grid = new Grid<>(MoneyBox.class);
        grid.setItems(moneyBoxes);


        grid.removeColumnByKey("value");
        grid.removeColumnByKey("currency");
        grid.removeColumnByKey("type");
        grid.removeColumnByKey("amount");

        grid.addColumn(MoneyBox::getValue).setHeader("Wert");
        grid.addColumn(MoneyBox::getCurrency).setHeader("Währung");
        //grid.addColumn(MoneyBox::getType).setHeader("Typ");
        grid.addColumn(MoneyBox::getAmount).setHeader("Anzahl");


        add(grid);
        return grid;
    }
}
