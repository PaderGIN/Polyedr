//Класс, обеспечивающий изображение проекции полиэдра.
public class SimpleDrawer extends SwingDrawer{
    //Полиэдр.
    protected Polyedr p;

    //Единичный вектор проектирования.
    protected R3Vector pr;

    //Единичный x-вектор плоскости проектирования.
    private R3Vector x;

    //Единичный y-вектор плоскости проектирования.
    private R3Vector y;

    //Минимальная x-координата проекции полиэдра.
    private double xmin;

    //Минимальная y-координата проекции полиэдра.
    private double ymin;

    //Размер проекции полиэдра.
    private double size;

    //Конструктор класса: p -- полиэдр, pr -- вектор проектирования,
    //angle -- угол поворота в плоскости проекции.
    public SimpleDrawer(Polyedr p, R3Vector pr, double angle){
        this.p = p;
        this.pr = pr.normalize();

        double a = pr.getX();
        double b = pr.getY();
        double c = pr.getZ();

        if (a != 0. || b != 0.){
            x = new R3Vector(-b, a, 0.);
        }else {
            x = new R3Vector(0., c, -b);
        }

        y = R3Vector.vectMul(x, pr);

        x.normalize();
        y.normalize();

        R3Vector nx = R3Vector.plus(R3Vector.mul(Math.cos(angle), x), R3Vector.mul(-Math.sin(angle), y));
        R3Vector ny = R3Vector.plus(R3Vector.mul(Math.sin(angle), x), R3Vector.mul(Math.cos(angle), y));

        x = nx;
        y = ny;

        xmin = ymin = Double.MAX_VALUE;
        double xmax, ymax;
        xmax = ymax = Double.MIN_VALUE;
        for(int i = 0; i < p.getVertexesQuantity(); i++){
            double xi = xProection(p.getVertex(i));
            double yi = yProection(p.getVertex(i));

            if(xi < xmin) xmin = xi;
            if(yi < ymin) ymin = yi;
            if(xi > xmax) xmax = xi;
            if(yi > ymax) ymax = yi;
        }

        size = ymax - ymin;
        if(xmax - xmin > size)
            size = xmax - xmin;
    }

    //Вычислить x-координату проекции точки.
    private double xProection(R3Vector v){
        return R3Vector.scalMul(v, x);
    }

    //Вычислить y-координату проекции точки.
    private double yProection(R3Vector v){
        return R3Vector.scalMul(v, y);
    }

    //Вычислить нормализованную x-координату проекции точки.
    protected double xnProection(R3Vector v){
        return (xProection(v) - xmin)/size;
    }

    //Вычислить нормализованную y-координату проекции точки.
    protected double ynProection(R3Vector v){
        return (yProection(v) - ymin)/size;
    }

    //Изобразить проекцию полиэдра.
    public final void draw(){
        for(int i = 0; i < p.getEdgesQuantity(); i++)
            drawEdge(p.getEdge(i));

        System.out.print("\n");
    }

    //Изобразить ребро полиэдра.
    public void drawEdge(Edge s){
        Vertex begin = s.getBegin();
        Vertex end = s.getEnd();

        draw(xnProection(begin), ynProection(begin), xnProection(end), ynProection(end));
        System.out.print(".");
    }
}