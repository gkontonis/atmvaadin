package frontend.src.main.java.atm.frontend.views;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import org.claspina.confirmdialog.ButtonOption;
import org.claspina.confirmdialog.ConfirmDialog;

public class CloseDialog {

    public void printDialog(){
        ConfirmDialog
                .createQuestion()
                .withCaption("Bitte bestätigen")
                .withMessage("Sind Sie sich sicher, dass Sie das Programm beenden wollen?")
                .withOkButton(() -> UI.getCurrent().navigate("closeview"), ButtonOption.caption("Beenden"))
                .withIcon(new Icon(VaadinIcon.THUMBS_DOWN_O))
                .withCancelButton(ButtonOption.caption("Abbrechen"), ButtonOption.icon(VaadinIcon.CLOSE))
                .open();
    }

}
