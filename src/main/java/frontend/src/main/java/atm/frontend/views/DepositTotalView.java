package frontend.src.main.java.atm.frontend.views;

import business.src.main.java.atm.business.depositView.DepositViewController;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;


@Route(value = "deposittotalview")
@PageTitle("Einzahlung")
public class DepositTotalView extends VerticalLayout {
    public static final String VIEW_ID = "deposittotalview";

    private DepositViewController controller = new DepositViewController();

    public DepositTotalView() {
        add(depositView());
    }

    public VerticalLayout depositView() {
        VerticalLayout depositView = new VerticalLayout();
        TextField inputfield = new TextField();
        Button confirmDepositButton = new Button("Einzahlen");
        confirmDepositButton.addClickListener(buttonClickEvent -> controller.depositTotal(inputfield.getValue()));

        Button backButton = new Button("Zurück");
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(DepositMenuView.VIEW_ID));

        depositView.add(inputfield, confirmDepositButton, backButton);
        return depositView;
    }
}
