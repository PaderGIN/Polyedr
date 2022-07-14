import java.util.Scanner;

//Класс, реализующий радиус-вектор в трехмерном пространстве.
public class R3Vector{
    //Координаты вектора.
    private double x, y, z;

    //Конструктор класса.
    public R3Vector(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    //Конструктор класса.
    public R3Vector() throws Exception {
        //Исключительная ситуация возникает при ошибках ввода координат с клавиатуры.

        Scanner in = new Scanner(System.in);

        System.out.print("x-координата вектора проектирования -> ");
        x = in.nextInt();
        //System.out.print("\n");

        System.out.print("y-координата вектора проектирования -> ");
        y = in.nextInt();
        //System.out.print("\n");

        System.out.print("z-координата вектора проектирования -> ");
        z = in.nextInt();
        //System.out.print("\n");
    }

    //Получить x-координату вектора.
    public final double getX(){
        return x;
    }

    //Получить y-координату вектора.
    public final double getY(){
        return y;
    }

    //Получить z-координату вектора.
    public final double getZ(){
        return z;
    }

    //Нормировать ненулевой вектор.
    public final R3Vector normalize(){
        double norm = Math.sqrt(x*x + y*y + z*z);
        x /= norm;
        y /= norm;
        z /= norm;
        return this;
    }

    //Добавить заданный вектор.
    public final R3Vector plus(R3Vector b){
        x += b.x;
        y += b.y;
        z += b.z;
        return this;
    }

    //Найти сумму двух векторов.
    public static R3Vector plus(R3Vector a, R3Vector b){
        return new R3Vector(a.x + b.x, a.y + b.y, a.z + b.z);
    }

    //Вычесть заданный вектор.
    public final R3Vector minus(R3Vector b){
        x -= b.x;
        y -= b.y;
        z -= b.z;
        return this;
    }

    //Найти разность двух векторов.
    public static R3Vector minus(R3Vector a, R3Vector b){
        return new R3Vector(a.x - b.x, a.y - b.y, a.z - b.z);
    }

    //Умножить вектор на заданное число.
    public final R3Vector mul(double k){
        x *= k;
        y *= k;
        z *= k;
        return this;
    }

    //Найти произведение вектора на число.
    public static R3Vector mul(double k, R3Vector a){
        return new R3Vector(k*a.x, k*a.y, k*a.z);
    }

    //Найти скалярное произведение векторов.
    public static double scalMul(R3Vector a, R3Vector b){
        return a.x*b.x + a.y*b.y + a.z*b.z;
    }

    //Найти векторное произведение векторов.
    public static R3Vector vectMul(R3Vector a, R3Vector b){
        return new R3Vector(a.y*b.z - a.z*b.y, a.z*b.x - a.x*b.z, a.x*b.y - a.y*b.x);
    }
}