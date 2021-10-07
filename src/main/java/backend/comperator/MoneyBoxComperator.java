package backend.comperator;

import backend.entity.MoneyBox;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class MoneyBoxComperator implements Comparator<MoneyBox> {

    public static final int GREATER = 1;
    public static final int EQUALS = 0;
    public static final int SMALLER = -1;

    @Override
    public int compare(MoneyBox o1, MoneyBox o2) {
        if (o1.getType() == MoneyBox.CurrencyType.NOTE && o2.getType() == MoneyBox.CurrencyType.COIN) {
            return GREATER;
        }

        if (o1.getType() == MoneyBox.CurrencyType.COIN && o2.getType() == MoneyBox.CurrencyType.NOTE) {
            return SMALLER;
        }

        return normResult(o1.getValue() - o2.getValue());
    }

    private int normResult(int comparison) {
        if (comparison == 0) {
            return EQUALS;
        }
        return comparison > 0 ? GREATER : SMALLER;
    }

    @Override
    public Comparator<MoneyBox> reversed() {
        return null;
    }

    @Override
    public Comparator<MoneyBox> thenComparing(Comparator<? super MoneyBox> other) {
        throw new IllegalArgumentException("Not supported");
    }

    @Override
    public <U> Comparator<MoneyBox> thenComparing(Function<? super MoneyBox, ? extends U> keyExtractor, Comparator<? super U> keyComparator) {
        throw new IllegalArgumentException("Not supported");
    }

    @Override
    public <U extends Comparable<? super U>> Comparator<MoneyBox> thenComparing(Function<? super MoneyBox, ? extends U> keyExtractor) {
        throw new IllegalArgumentException("Not supported");
    }

    @Override
    public Comparator<MoneyBox> thenComparingInt(ToIntFunction<? super MoneyBox> keyExtractor) {
        throw new IllegalArgumentException("Not supported");
    }

    @Override
    public Comparator<MoneyBox> thenComparingLong(ToLongFunction<? super MoneyBox> keyExtractor) {
        throw new IllegalArgumentException("Not supported");
    }

    @Override
    public Comparator<MoneyBox> thenComparingDouble(ToDoubleFunction<? super MoneyBox> keyExtractor) {
        throw new IllegalArgumentException("Not supported");
    }
}
