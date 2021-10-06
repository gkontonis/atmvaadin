package backend.comperator;

import backend.entity.MoneyBox;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Comperator is used for sorting MoneyBoxes in Collections.
 * It is designed to sort Notes before Coins and values from biggest to smallest (Decending)
 */
public class MoneyBoxDecendingComperator implements Comparator<MoneyBox> {

    public static final int GREATER = 1;
    public static final int EQUALS = 0;
    public static final int SMALLER = -1;

    @Override
    public int compare(MoneyBox o1, MoneyBox o2) {
        if (o1.getType() == MoneyBox.CurrencyType.NOTE && o2.getType() == MoneyBox.CurrencyType.COIN) {
            return SMALLER;
        }

        if (o1.getType() == MoneyBox.CurrencyType.COIN && o2.getType() == MoneyBox.CurrencyType.NOTE) {
            return GREATER;
        }
        return  o2.getValue()-o1.getValue();
    }

    @Override
    public Comparator<MoneyBox> reversed() {
        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public Comparator<MoneyBox> thenComparing(Comparator<? super MoneyBox> other) {
        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public <U> Comparator<MoneyBox> thenComparing(Function<? super MoneyBox, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<MoneyBox> thenComparing(Function<? super MoneyBox, ? extends U> keyExtractor) {
        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public Comparator<MoneyBox> thenComparingInt(ToIntFunction<? super MoneyBox> keyExtractor) {
        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public Comparator<MoneyBox> thenComparingLong(ToLongFunction<? super MoneyBox> keyExtractor) {
        throw new IllegalStateException("Not yet implemented");
    }

    @Override
    public Comparator<MoneyBox> thenComparingDouble(ToDoubleFunction<? super MoneyBox> keyExtractor) {
        return null;
    }
}