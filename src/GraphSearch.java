import java.util.*;

public class GraphSearch {

    public DefaultResult internalSearch(Vertex src, Vertex dst , Graph graph) {

        DefaultResult result = new DefaultResult(src, dst, 1);
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new PathComparator(result));
        Set<Vertex> visited = new HashSet<>();
        pq.add(src);
        result.updateVertex(src, null, 0);

        while (!pq.isEmpty()) {
            Vertex nearest = pq.poll();
            if (nearest.equals(dst)) {
                break;
            }
            visited.add(nearest);
            for (Edge edge : graph.getEagesFrom(nearest)) {

                Vertex d = edge.dst;
                if (!visited.contains(d)) {
                    result.relaxEdge(edge);
                    pq.add(d);
                }
            }
        }
        result.buildPath();
        return result;
    }
    public DefaultResult internalSearch(Vertex src, Vertex dst, Graph graph, int maxPaths) {

        DefaultResult result = new DefaultResult(src, dst, maxPaths);
        PriorityQueue<Vertex> pq = new PriorityQueue<>(new PathComparator(result));
        Set<Vertex> visited = new HashSet<>();
        pq.add(src);
        result.updateVertexMuti(src, null, 0);

        while (!pq.isEmpty()) {
            Vertex nearest = pq.poll();
            visited.add(nearest);
            if (nearest.equals(dst)) {
                break;
            }
            for (Edge edge : graph.getEagesFrom(nearest)) {
                Vertex d = edge.dst();
                if (!visited.contains(d)) {
                    result.relaxEdgeMuti(edge);
                    pq.add(d);
                }
            }
        }
        result.buildAllPath();
        return result;
    }

    private class PathComparator implements Comparator<Vertex> {

        DefaultResult result;

        public PathComparator (DefaultResult result) {
            this.result = result;
        }

        @Override
        public int compare(Vertex v1, Vertex v2) {
            if (!result.hasCost(v1) && result.hasCost(v2)) {
                return 0;
            } else if (!result.hasCost(v1)) {
                return 1;
            } else if (!result.hasCost(v2)){
                return -1;
            }
            return Double.compare(result.cost(v1), result.cost(v2));
        }
    }

}
