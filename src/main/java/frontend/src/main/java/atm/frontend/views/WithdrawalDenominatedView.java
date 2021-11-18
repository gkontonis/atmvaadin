package frontend.src.main.java.atm.frontend.views;

import business.src.main.java.atm.business.statusview.StatusViewController;
import business.src.main.java.atm.business.withdrawalview.WithdrawalViewController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import frontend.src.main.java.atm.frontend.components.notifications.FancyNotification;

@Route(value = WithdrawalDenominatedView.VIEW_ID)
@PageTitle("Auszahlung")
public class WithdrawalDenominatedView extends VerticalLayout {
    public static final String VIEW_ID = "withdrawaldenominatedview";

    private StatusViewController statusViewController = new StatusViewController();

    private WithdrawalViewController controller = new WithdrawalViewController();
    private FancyNotification fancyNotification = new FancyNotification();

    public WithdrawalDenominatedView() {
        add(withdrawalDenominatedView());
    }

    private VerticalLayout withdrawalDenominatedView() {
        VerticalLayout withdrawalView = new VerticalLayout();
        //TODO OO Text text = new Text(statusViewController.getStatus());

        TextField inputfield = new TextField();
        Label lable = new Label("Bitte die Beträge eingeben die Sie einzahlen wollen (Form: TypWährungAnzahl z.B. 10A15 5A33)");
        Button confirmWithdrawalButton = new Button("Abheben");

        confirmWithdrawalButton.addClickListener(buttonClickEvent -> {

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

       // withdrawalView.add(text, inputfield, confirmWithdrawalButton, backButton);
        withdrawalView.add(lable, inputfield, confirmWithdrawalButton, backButton);

        return withdrawalView;
    }

}
