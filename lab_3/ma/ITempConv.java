package ma;
import java.util.function.Function;

public interface ITempConv<T extends Number> extends Function<T, Double>{
    Double apply(T t);
} 