package frontend.src.main.java.atm.frontend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;

//TODO: Sugar after it works @CssImport("\u202AC:\\Users\\02uz0408\\Desktop\\ATMVaadin\\src\\main\\java\\frontend\\frontend\\styles\\mainView.css")
@Route(value = MainView.VIEW_ID)
@PWA(name = "My Application", shortName = "My Application")
public class MainView extends VerticalLayout {

    public static final String VIEW_ID = "";


    public MainView() {
        addClassName("mainView");
        add(greetingsLabel());
        add(mainMenuButtons());
    }

    private VerticalLayout mainMenuButtons() {
        VerticalLayout buttonLayout = new VerticalLayout();
        buttonLayout.addClassName("menuButtons");

        Button depositButton = new Button("Einzahlung");
        Button withdrawalButton = new Button("Auszahlung");
        Button statusButton = new Button("Status");
        Button closeProgramButton = new Button("Beenden");

        depositButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(DepositMenuView.VIEW_ID));
        withdrawalButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(WithdrawalMenuView.VIEW_ID));
        statusButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(StatusView.VIEW_ID));
        closeProgramButton.addClickListener(buttonClickEvent -> new CloseDialog().printDialog());

        buttonLayout.add(depositButton, withdrawalButton, statusButton, closeProgramButton);

        return buttonLayout;
    }

    private Label greetingsLabel() {
        Label header = new Label("Wilkommen beim Lehrlings ATM! Was möchten Sie tun?");
        header.addClassName("label");
        return header;
    }
}
