package frontend.src.main.java.atm.frontend.views;

import backend.entity.MoneyBoxItem;
import com.vaadin.flow.component.grid.Grid;

import java.util.List;
import java.util.Map;

public class GridCaptionHelper {

    public static void setGridCaptions(List<Grid.Column<MoneyBoxItem>> columns, Map<String, String> captions) {

        for(Grid.Column<MoneyBoxItem> column : columns) {

            String caption = captions.get(column.getKey());

            if(caption == null || caption.isEmpty()) {
                caption = column.getKey().toUpperCase();
            }

            column.setHeader(caption);
        }
    }


}
