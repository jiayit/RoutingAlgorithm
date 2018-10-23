import java.util.*;

public class DefaultResult {

    Vertex src;
    Vertex dst;
    int maxPaths;
    HashMap<Vertex, Double> costs = new HashMap<>();
    HashMap<Vertex, Edge> parents = new HashMap<>();
    HashMap<Vertex, Set<Edge>> mutiParents = new HashMap<>();
    Path path;
    Set<Path> paths = new HashSet<>();


    public DefaultResult(Vertex src, Vertex dst, int maxPaths) {
        this.src = src;
        this.dst = dst;
        this.maxPaths = maxPaths;
    }

    public Vertex src() {
        return src;
    }

    public Vertex dst() {
        return dst;
    }

    public boolean hasCost(Vertex v) {
        return costs.containsKey(v);
    }

    public Double cost(Vertex v) {
        return costs.get(v);
    }


    public Map<Vertex, Double> costs() {
        return costs;
    }

    public Path path() {
        return path;
    }

    public Set<Path> paths() {
        return paths;
    }

    public void buildPath() {
        Vertex firstVertex = dst;
        Path pendingPath = new Path();
        pendingPath.cost = cost(dst);
        while (!firstVertex.equals(src)) {
            Edge parent = parents.get(firstVertex);
            pendingPath.insertEdge(parent);
            firstVertex = parent.src;
        }
        path = new Path(pendingPath);
    }

    public void buildAllPath() {
        Path basePath = new Path();
        basePath.cost = cost(dst);
        Set<Path> pendingPaths = new HashSet<>();
        pendingPaths.add(basePath);
        while (!pendingPaths.isEmpty() && paths.size() < maxPaths) {
            Set<Path> frontier = new HashSet<>();
            for (Path path : pendingPaths) {
                Vertex first = firstVertex(path, dst);
                if (first.equals(src)) {
                    paths.add(new Path(path));
                } else {
                    Set<Edge> edges = mutiParents.get(first);
                    Iterator<Edge> e = edges.iterator();
                    while (e.hasNext()) {
                        Edge edge = e.next();
                        boolean last = ! e.hasNext();
                        Path pendingPath = last ? path : new Path(path);
                        pendingPath.insertEdge(edge);
                        frontier.add(pendingPath);
                    }
                }
            }
            pendingPaths = frontier;
        }
    }

    public void relaxEdge(Edge edge) {
        Vertex d = edge.dst;
        double newCost = cost(edge.src) + edge.weight;
        double oldCost;
        int compareResult = -1;
        if (hasCost(d)) {
            oldCost = cost(d);
            compareResult = Double.compare(newCost, oldCost);
        }
        if (compareResult <= 0) {
            updateVertex(d, edge, newCost);
        }
    }

    public void updateVertex(Vertex v, Edge e, double cost) {
        costs.put(v, cost);
        if (e != null) {
            parents.put(v, e);
        }
    }


    public void relaxEdgeMuti(Edge edge) {
        Vertex d = edge.dst;
        double newCost = cost(edge.src) + edge.weight;
        double oldCost;
        int compareResult = -1;
        if (hasCost(d)) {
            oldCost = cost(d);
            compareResult = Double.compare(newCost, oldCost);
        }
        if (compareResult <= 0) {
            updateVertexMuti(d, edge, newCost);
        }
    }

    public void updateVertexMuti(Vertex v, Edge e, double cost) {
        costs.put(v, cost);
        if (e != null) {
            Set<Edge> edges = mutiParents.get(v);
            if (edges == null) {
                edges = new HashSet<>();
                mutiParents.put(v, edges);
            }
            if (edges.size() <= maxPaths) {
                edges.add(e);
            }
        }
    }

    public Vertex firstVertex(Path path, Vertex dst) {
        return path.edges().isEmpty() ? dst : path.edges().get(0).src;
    }
}
