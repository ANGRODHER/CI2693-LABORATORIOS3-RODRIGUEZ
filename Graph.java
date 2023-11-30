import java.util.List;

public interface Graph<v> {
    public boolean add(v vertex);

    public boolean connect(v from, v to, double valor);

    public boolean disconnect(v from, v to);

    public boolean contains(v vertex);

    public List<Moneda> getInwardEdges(v to);

    public List<Moneda> getOutwardEdges(v from);

    public List<Moneda> getVerticesConnectedTo(v vertex);

    public List<Moneda> getAllVertices();

    public boolean remove(v vertex);

    public int size();
}