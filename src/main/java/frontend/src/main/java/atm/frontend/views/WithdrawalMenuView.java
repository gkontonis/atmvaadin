package frontend.src.main.java.atm.frontend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = WithdrawalMenuView.VIEW_ID)
@PageTitle("Auszahlung")
public class WithdrawalMenuView extends VerticalLayout {

    public static final String VIEW_ID = "withdrawalmenuview";

    public WithdrawalMenuView() {
        addClassName("withdrawalMenuView");
        add(label());
        add(withdrawalMenuButtons());
    }

    private VerticalLayout withdrawalMenuButtons() {
        VerticalLayout withdrawalButtonLayout = new VerticalLayout();
        withdrawalButtonLayout.addClassName("withdrawalMenuButtons");

        Button wholeValueButton = new Button("Gesamtbetrag");
        Button denominatedValueButton = new Button("Gestückelt");
        Button backButton = new Button("Zurück");

        wholeValueButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(WithdrawalTotalView.VIEW_ID));
        denominatedValueButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(WithdrawalDenominatedlView.VIEW_ID));
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(""));

        withdrawalButtonLayout.add(wholeValueButton, denominatedValueButton, backButton);

        return withdrawalButtonLayout;
    }

    private Label label() {
        Label header = new Label("Auszahlung:");
        header.addClassName("label");
        return header;
    }
}
