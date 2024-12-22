package ma;



public class TempConv<T extends Number> implements ma.ITempConv<T>{
    public Double apply(T t) {
        return t.doubleValue() * (9/5) + 32;
    }
}

