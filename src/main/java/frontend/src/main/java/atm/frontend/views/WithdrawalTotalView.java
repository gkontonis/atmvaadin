package frontend.src.main.java.atm.frontend.views;

import business.src.main.java.atm.business.withdrawalView.WithdrawalViewController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = WithdrawalTotalView.VIEW_ID)
@PageTitle("Auszahlung")
public class WithdrawalTotalView extends VerticalLayout {
    public static final String VIEW_ID = "withdrawaltotalview";

    private WithdrawalViewController controller = new WithdrawalViewController();

    public WithdrawalTotalView() {
        add(withdrawalTotalView());
    }

    private VerticalLayout withdrawalTotalView() {
        VerticalLayout withdrawalView = new VerticalLayout();
        TextField inputfield = new TextField();
        Button confirmWithdrawalButton = new Button("Abheben");
        confirmWithdrawalButton.addClickListener(buttonClickEvent -> controller.withdrawal(inputfield.getValue()));

        Button backButton = new Button("Zurück");
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(WithdrawalMenuView.VIEW_ID));

        withdrawalView.add(inputfield, confirmWithdrawalButton, backButton);

        return withdrawalView;
    }

}
