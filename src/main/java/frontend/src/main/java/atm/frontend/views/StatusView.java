package frontend.src.main.java.atm.frontend.views;

import business.src.main.java.atm.business.statusView.StatusViewController;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.Label;


@Route(value = StatusView.VIEW_ID)
@PageTitle("Status")
public class StatusView extends VerticalLayout {
    public static final String VIEW_ID = "statusview";
    private StatusViewController controller = new StatusViewController();


    public StatusView(){
        addClassName("statusView");
        add(label());
        add(statusMenuButtons());
    }

    private VerticalLayout statusMenuButtons(){
        VerticalLayout statusLayout = new VerticalLayout();
        statusLayout.addClassName("statusMenuButtons");

        Text status = new Text(controller.getStatus());

        Button backButton = new Button("Zurück");
        backButton.addClickListener(buttonClickEvent -> UI.getCurrent().navigate(""));

        statusLayout.add(status, backButton);

        return statusLayout;
    }

    private Label label() {
        Label statusLable = new Label("Status");
        statusLable.addClassName("label");
        return statusLable;
    }
}
