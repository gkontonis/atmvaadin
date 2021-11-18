package frontend.src.main.java.atm.frontend.views;

import business.src.main.java.atm.business.depositview.DepositViewController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import frontend.src.main.java.atm.frontend.components.notifications.FancyNotification;


@Route(value = "deposittotalview")
@PageTitle("Einzahlung")
public class DepositTotalView extends VerticalLayout {
    public static final String VIEW_ID = "deposittotalview";

    private DepositViewController controller = new DepositViewController();
    private FancyNotification fancyNotification = new FancyNotification();

    public DepositTotalView() {
        add(depositView());
    }

    public VerticalLayout depositView() {
        VerticalLayout depositView = new VerticalLayout();
        TextField inputfield = new TextField();
        Button confirmDepositButton = new Button("Einzahlen");

        confirmDepositButton.addClickListener(buttonClickEvent -> {
            try {
                controller.depositTotal(inputfield.getValue());
            } catch (IllegalArgumentException e) {
                fancyNotification.showErrorNotification("Ungültige Eingabe.", 2000);    //TODO: Print stacktrace once Return Nulls are all replaced by exceptions
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
