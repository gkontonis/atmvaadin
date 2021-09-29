package Calculation;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class MoneyBoxComperator implements Comparator<MoneyBox> {

    @Override
    public int compare(MoneyBox o1, MoneyBox o2) {
        //TODO: Doenst sort right, fix
        int valueResult = o2.getValue() - o1.getValue();

        if(valueResult==0){
            if(o1.getType()==o2.getType()){
                return 0;
            }
            if(o1.getType()== MoneyBox.CurrencyType.NOTE){
                return -1;
            }
            return 1;
        }

        return valueResult;
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
