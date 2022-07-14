import java.util.*;
import java.io.*;

//Класс, реализующий полиэдр.
public class Polyedr{
    //Массив вершин полиэдра.
    private Vertex[] vertexes;

    //Массив ребер полиэдра.
    private Edge[] edges;

    //Массив граней полиэдра.
    private Facet[] facets;

    //Конструктор класса.
    public Polyedr(String file) throws Exception{
        //Исключительная ситуация, возникающая при ошибках чтения или преобразования данных.

        RandomAccessFile f = new RandomAccessFile(file, "r");
        StringTokenizer st = new StringTokenizer(f.readLine());

        vertexes = new Vertex[Integer.parseInt(st.nextToken())];
        facets = new Facet[Integer.parseInt(st.nextToken())];
        edges = new Edge[Integer.parseInt(st.nextToken())];

        for(int i = 0; i < vertexes.length; i++){
            st = new StringTokenizer(f.readLine());

            double x = Double.valueOf(st.nextToken()).doubleValue();
            double y = Double.valueOf(st.nextToken()).doubleValue();
            double z = Double.valueOf(st.nextToken()).doubleValue();

            vertexes[i] = new Vertex(x, y, z);
        }

        int k = 0;
        for(int i = 0; i < facets.length; i++){
            st = new StringTokenizer(f.readLine());
            int size = Integer.parseInt(st.nextToken());

            Vertex[] facet= new Vertex[size];

            facet[0] = vertexes[Integer.parseInt(st.nextToken()) - 1];
            for(int j = 1; j < size; j += 1){
                facet[j] = vertexes[Integer.parseInt(st.nextToken()) - 1];
                edges[k++] = new Edge(facet[j], facet[j - 1]);
            }

            edges[k++] = new Edge(facet[size - 1], facet[0]);
            facets[i] = new Facet(facet);
        }
    }

    //Получить количество вершин.
    public final int getVertexesQuantity(){
        return vertexes.length;
    }

    //Получить вершину с номером i.
    public final Vertex getVertex(int i){
        return vertexes[i];
    }

    //Получить количество ребер.
    public final int getEdgesQuantity(){
        return edges.length;
    }

    //Получить ребро с номером i.
    public final Edge getEdge(int i){
        return edges[i];
    }

    //Получить количество граней.
    public final int getFacetsQuantity(){
        return facets.length;
    }

    //Получить грань с номером i.
    public final Facet getFacet(int i){
        return facets[i];
    }
}