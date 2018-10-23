import java.util.*;

public class Graph {
    Set<Vertex> vertices = new HashSet<>();
    Set<Edge> edges = new HashSet<>();
    HashMap<Vertex, Set<Edge>> srcMap = new HashMap<>();
    HashMap<Vertex, Set<Edge>> dstMap = new HashMap<>();
    public Graph (Set<Vertex> vertexSet, Set<Edge> edgeSet) {
        this.vertices = vertexSet;
        this.edges = edgeSet;
        for (Edge edge : edgeSet) {
            Set<Edge> srcEdges = srcMap.get(edge.src());
            if (srcEdges == null) {
                srcEdges = new HashSet<>();
                srcMap.put(edge.src(), srcEdges);
            }
            srcEdges.add(edge);

            Set<Edge> dstEdges = dstMap.get(edge.dst());
            if (dstEdges == null) {
                dstEdges = new HashSet<>();
                dstMap.put(edge.dst(), dstEdges);
            }
            dstEdges.add(edge);
        }
    }

    public Set<Edge> getEdges() {
        return edges;
    }

    public Set<Vertex> getVertices() {
        return vertices;
    }

    public Set<Edge> getEagesFrom(Vertex src) {
        Set<Edge> set = srcMap.get(src);
        if (set == null) return Collections.EMPTY_SET;
        return set;
    }
    public Set<Edge> getEagesTo(Vertex dst) {
        Set<Edge> set = dstMap.get(dst);
        if (dst == null) return Collections.EMPTY_SET;
        return set;
    }

    public int getEdgeNumber() {
        return edges.size();
    }
}
