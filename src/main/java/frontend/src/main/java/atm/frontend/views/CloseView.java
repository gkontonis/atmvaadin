package frontend.src.main.java.atm.frontend.views;


import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "closeview")
@PageTitle("Beenden")
public class CloseView extends VerticalLayout {

    public static final String VIEW_ID = "closeview";

    public CloseView(){
        add(byeText());
    }

    private VerticalLayout byeText() {
        VerticalLayout byeLayout = new VerticalLayout();
        Text byeText = new Text("Auf wiedersehen!");
        byeLayout.add(byeText);
        byeLayout.setAlignItems(Alignment.CENTER);
        return byeLayout;
    }
}
