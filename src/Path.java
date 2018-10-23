import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Path{

    List<Edge> edges = new ArrayList<>();
    double cost;

    public Path() {

    }

    /**
     * Creates a new path as a copy of another path.
     *
     * @param path path to be copied
     */
    public Path(Path path) {
        this.cost = path.cost();
        edges.addAll(path.edges());
    }

    public Path(Path path, double cost) {
        this.cost = cost;
        edges.addAll(path.edges);
    }
    public Vertex src() {
        return edges.isEmpty() ? null : edges.get(0).src;
    }

    public Vertex dst() {
        return edges.isEmpty() ? null : edges.get(edges.size() - 1).dst();
    }

    public double cost() {
        return cost;
    }
    public List<Edge> edges() {
        return edges;
    }

    public void insertEdge(Edge edge) {
       if(edges.isEmpty() || src().equals(edge.dst())) {
           edges.add(0, edge);
       }
    }

    public void appendEdge(Edge edge) {
        if (edges.isEmpty() || dst().equals(edge.src())) {
            edges.add(edge);
        }
    }

    public void removeEdge(Edge edge) {
        edges.remove(edge);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cost, edges);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return  true;
        }
        if (obj instanceof Path) {
            return Objects.equals(this.cost, ((Path) obj).cost)
                    && Objects.equals(this.edges, ((Path) obj).edges);
        }
        return false;
    }
}
