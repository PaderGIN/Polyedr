//Класс, реализующий одномерный отрезок.
public class Segment{
    //Координаты начала и конца отрезка.
    private double begin, end;

    //Конструктор класса.
    public Segment(double begin, double end){
        this.begin = begin;
        this.end = end;
    }

    //Вырожден ли отрезок?
    public final boolean degenerate(){
        return begin >= end;
    }

    //Найти левый отрезок разности с отрезком s.
    public final Segment leftSub(Segment s){
        return new Segment(begin, Math.min(end, s.begin));
    }

    //Найти правый отрезок разности с отрезком s.
    public final Segment rightSub(Segment s) {
        return new Segment(Math.max(begin, s.end), end);
    }

    //Найти пересечение с отрезком s.
    public final Segment intersection(Segment s){
        begin = Math.max(begin, s.begin);
        end = Math.min(end, s.end);
        return this;
    }

    //Получить начало отрезка.
    public final double getBegin(){
        return begin;
    }

    //Получить конец отрезка.
    public final double getEnd(){
        return end;
    }
}