package frontend.src.main.java.atm.frontend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "depositmenuview")
@PageTitle("Einzahlung")
public class DepositMenuView extends VerticalLayout {
    public static final String VIEW_ID = "depositmenuview";

    public DepositMenuView() {
        addClassName("depositMenuView");
        add(label());
        add(depositMenuButtons());
    }

    private VerticalLayout depositMenuButtons() {
        VerticalLayout depositButtonLayout = new VerticalLayout();
        depositButtonLayout.addClassName("depositMenuButtons");

        Button totalValueButton = new Button("Gesamtbetrag");
        Button denominatedValueButton = new Button("Gestückelt");
        Button backButton = new Button("Zurück");

        totalValueButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(DepositTotalView.VIEW_ID));
        denominatedValueButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(DepositDenominatedView.VIEW_ID));
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(""));

        depositButtonLayout.add(totalValueButton, denominatedValueButton, backButton);

        return depositButtonLayout;
    }

    private Label label() {
        Label header = new Label("Einzahlung:");
        header.addClassName("label");
        return header;
    }
}
