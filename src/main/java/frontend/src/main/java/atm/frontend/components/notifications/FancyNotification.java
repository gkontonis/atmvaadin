package frontend.src.main.java.atm.frontend.components.notifications;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

public class FancyNotification {
    private Notification notification;

    public void showErrorNotification(String text, Integer duration){
        Notification notification = new Notification(text, duration, Notification.Position.MIDDLE);
        notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
        notification.open();
    }

    public void showSuccessNotification(String text, Integer duration){
        Notification notification = new Notification(text, duration, Notification.Position.BOTTOM_END);
        notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
        notification.open();
    }

}
