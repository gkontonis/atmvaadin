package frontend.src.main.java.atm.frontend.views;

import business.src.main.java.atm.business.depositView.DepositViewController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import frontend.src.main.java.atm.frontend.components.notifications.FancyNotification;


@Route(value = DepositDenominatedView.VIEW_ID)
@PageTitle("Einzahlung")
public class DepositDenominatedView extends VerticalLayout {
    public static final String VIEW_ID = "depositdenominatedview";

    private DepositViewController controller = new DepositViewController();
    private FancyNotification fancyNotification = new FancyNotification();

    public DepositDenominatedView() {
        add(depositView());
    }

    public VerticalLayout depositView() {
        VerticalLayout depositView = new VerticalLayout();
        TextField inputfield = new TextField();
        Button confirmDepositButton = new Button("Einzahlen");

        confirmDepositButton.addClickListener(buttonClickEvent -> {
            //if (!isValid()) {
            //    //ThrowError
            //    fancyNotification.showErrorNotification("Ungültige Eingabe.", 2000);
            //    return;
            //}
            //TODO: Write Validator instead of try catch

            try {
                controller.depositDenominated(inputfield.getValue());
            } catch (IllegalArgumentException e) {
                fancyNotification.showErrorNotification("Ungültige Eingabe.", 2000);
                return;
            }
            fancyNotification.showSuccessNotification("Betrag erfolgreich eingezahlt.", 3000);
            UI.getCurrent().navigate(MainView.VIEW_ID);
        });


        Button backButton = new Button("Zurück");
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(DepositMenuView.VIEW_ID));

        depositView.add(inputfield, confirmDepositButton, backButton);
        return depositView;
    }
}
