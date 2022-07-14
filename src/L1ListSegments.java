//Класс, реализующий односвязный список отрезков.

public class L1ListSegments{
    //Массив отрезков.
    private Segment[] array;

    //Массив ссылок.
    private int[] next;

    //Нил списка.
    private int nilList;

    //Нил свободного места.
    private int nilFree;

    //Элемент до указателя.
    private int before;

    //Элемент за указателем.
    private int after;

    //Конструктор класса:
    //size -- максимальный размер списка.
    public L1ListSegments(int size){
        array = new Segment[size];
        next = new int[size + 2];

        nilList = size;
        nilFree = size + 1;

        link(nilList, nilList);
        link(nilFree, 0);

        for(int i = 0; i < size - 1; i++)
            link(i, i + 1);

        link(size - 1, nilFree);

        before = after = nilList;
    }

    //Связать два элемента.
    private void link(int first, int second){
        next[first] = second;
    }

    //Захватить место.
    private int mallocIndex(){
        int index = next[nilFree];
        link(nilFree, next[index]);
        return index; //Индекс занимаемого места.
    }

    //Освободить место.
    private void freeIndex(int index){
        link(index, next[nilFree]);
        link(nilFree, index);
    }

    //Пуст ли список?
    public final boolean empty(){
        return next[nilList] == nilList;
    }

    //Сделать список пустым.
    public final void clear(){
        try{
            toFront();
            while(true)
                erase();
        }catch(Exception e){ ; }
    }

    //Передвинуть указатель в начало списка.
    public final void toFront(){
        before = nilList;
        after = next[nilList];
    }

    //Указатель в конце списка?
    public final boolean end(){
        return after == nilList;
    }

    //Передвинуть указатель вперед.
    public final void forward() throws Exception{
        //Исключительная ситуация возникает при попытке передвинуть вперед указатель, находящийся в конце списка.

        if(after == nilList) throw new Exception();

        before = after;
        after = next[after];
    }

    //Получить элемент за указателем.
    public final Segment after() throws Exception{
        //Исключительная ситуация возникает при попытке получить элемент за указателем, находящимся в конце списка.

        return array[after];
    }

    //Добавить элекмент (отрезок, сегмент) за указателем.
    public final void insert(Segment val) throws Exception{
        //Исключетельная ситуация возникает при попытке добавить элемент в заполненный до конца список.

        int index = mallocIndex();

        link(before, index);
        link(index, after);
        after = index;

        array[index] = val;
    }

    //Удалить элемент за указателем.
    public final Segment erase() throws Exception{
        //Исключительная ситуация возникает при попытке удалить элемент из пустого списка.

        Segment val = array[after];

        int index = after;
        after = next[index];
        link(before, after);

        freeIndex(index);

        return val;
    }
}