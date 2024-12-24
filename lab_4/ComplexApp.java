public class ComplexApp {
    static class Complex<T extends Number>{
        T real, img;
        public Complex(T real, T imag){
            this.real = real;
            this.img = imag;
        }

        public Complex<Double> add(Complex<T> rOperand){
            double real = this.real.doubleValue() + rOperand.real.doubleValue();
            double img = this.img.doubleValue() + rOperand.img.doubleValue();
            return new Complex<Double>(real, img);
        }

        T getReal(){
            return this.real;
        }
        
        T getImg(){
            return this.img;
        }

        void setReal(T real){
            this.real = real;
        }
        
        void setImg(T img){
            this.img = img;
        }

        @Override
        public String toString(){
            return String.format("%d%s%s", real, (img.doubleValue() <= 0) ? "" : "+", img.doubleValue() == 0 ? "" : img.toString());
        }
    }


    public static void main(String[] args){
        System.out.println(
            new Complex<Number>(25, 0)
        );
    }
}
