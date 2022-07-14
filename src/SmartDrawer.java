/**
 * @author Е.А. Роганов
 * Класс SmartDrawer, обеспечивающий изображение проекции полиэдра
 * с использованием двумерного хеширования граней.
 */
public class SmartDrawer extends ShadowDrawer{
    /**
     * Количество гнезд сетки в строке и столбце.
     */
    private final static int SIZE = 50;

    /**
     * Максимальное число граней в гнезде.
     */
    private final static int MAXFACETS = 200;

    /**
     * Массив гнезд, в которые попадает обрабатываемое ребро.
     */
    private boolean[][] sockets;

    /**
     * Массив количеств граней в гнездах.
     */
    private int[][] nmbFacets;

    /**
     * Массив множеств номеров граней.
     */
    private int[][][] hashFacets;

    /**
     * Найти последний индекс гнезда, соответствующий t.
     * @param t Координата.
     */
    private int lastVal(double t){
        return Math.min((int)(t*SIZE), SIZE - 1);
    }

    /**
     * Найти первый индекс гнезда, соответствующий t.
     * @param t Координата.
     */
    private int firstVal(double t){
        return (int)(t*SIZE);
    }

    /**
     * Конструктор класса.
     * @param p Полиэдр.
     * @param pr Вектор проектирования.
     * @param angle Угол поворота в плоскости проекции.
     */
    public SmartDrawer(Polyedr p, R3Vector pr, double angle){
        super(p, pr, angle);

        nmbFacets = new int[SIZE][SIZE];
        hashFacets = new int[SIZE][SIZE][MAXFACETS];

        int i, j, k;

        int imax = p.getFacetsQuantity();

        for(i = 0; i < imax; i++){
            Facet f = p.getFacet(i);
            k = f.getVertexesQuantity();

            double x0 = 1., y0 = 1., x1 = 0., y1 = 0.;

            for(j = 0; j < k; j++){
                R3Vector v = f.getVertex(j);

                double x = xnProection(v);
                double y = ynProection(v);

                if(x < x0) x0 = x;
                if(y < y0) y0 = y;
                if(x > x1) x1 = x;
                if(y > y1)y1 = y;
            }

            int jm = lastVal(x1);
            int km = lastVal(y1);

            for(j = firstVal(x0); j <= jm; j++)
                for(k = firstVal(y0); k <= km; k++)
                    hashFacets[j][k][nmbFacets[j][k]++] = i;
        }
    }

    /**
     * Учесть тень от всех граней.
     */
    protected void addShadow(){
        int i, j, k;

        double x0 = Math.min(xnProection(begin), xnProection(end));
        double x1 = Math.max(xnProection(begin), xnProection(end));
        double y0 = Math.min(ynProection(begin), ynProection(end));
        double y1 = Math.max(ynProection(begin), ynProection(end));

        int jm = lastVal(x1);
        int km = lastVal(y1);

        for(j = firstVal(x0); j <= jm; j++)
            for(k = firstVal(y0); k<=km; k++)
                for(i = 0; i < nmbFacets[j][k]; i++)
                    addShadow(p.getFacet(hashFacets[j][k][i]));
    }
}