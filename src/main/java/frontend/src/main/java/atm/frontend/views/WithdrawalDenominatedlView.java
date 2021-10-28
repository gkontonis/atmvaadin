package frontend.src.main.java.atm.frontend.views;

import business.src.main.java.atm.business.withdrawalView.WithdrawalViewController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import frontend.src.main.java.atm.frontend.components.notifications.FancyNotification;

@Route(value = WithdrawalDenominatedlView.VIEW_ID)
@PageTitle("Auszahlung")
public class WithdrawalDenominatedlView extends VerticalLayout {
    public static final String VIEW_ID = "withdrawaldenominatedview";

    private WithdrawalViewController controller = new WithdrawalViewController();
    private FancyNotification fancyNotification = new FancyNotification();

    public WithdrawalDenominatedlView() {
        add(withdrawalDenominatedView());
    }

    private VerticalLayout withdrawalDenominatedView() {
        VerticalLayout withdrawalView = new VerticalLayout();
        TextField inputfield = new TextField();
        Button confirmWithdrawalButton = new Button("Abheben");

        confirmWithdrawalButton.addClickListener(buttonClickEvent -> {
            //if (!isValid()) {
            //    //ThrowError
            //    fancyNotification.showErrorNotification("Ungültige Eingabe.", 2000);
            //    return;
            //}

            try {
                controller.withdrawDenominated(inputfield.getValue());
            } catch (IllegalArgumentException e) {
                fancyNotification.showErrorNotification("Ungültige Eingabe.", 2000);    //TODO: Print stacktrace once Return Nulls are all replaced by exceptions
                return;
            }

            fancyNotification.showSuccessNotification("Betrag erfolgreich ausgezahlt.", 3000);
            UI.getCurrent().navigate(MainView.VIEW_ID);
        });

        Button backButton = new Button("Zurück");
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(WithdrawalMenuView.VIEW_ID));

        withdrawalView.add(inputfield, confirmWithdrawalButton, backButton);

        return withdrawalView;
    }

}
