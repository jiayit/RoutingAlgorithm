import java.util.List;
import java.util.Set;

public class Util {

    public void updateEdgeLoad(Flow flow, Edge edge) {
        edge.updateLoadbandwidth(flow.getLoadSpeed());
    }

    public void updateEdgeLoad(Flow flow, Path path) {
        for (Edge edge : path.edges()) {
            edge.updateLoadbandwidth(flow.getLoadSpeed());
        }
    }
    public void updateEdgeWeight(Flow flow, Path path) {

        for (Edge edge : path.edges()) {
            updateEdgeLoad(flow, edge);
            double load =  edge.weight + (double)edge.loadbandwidth / edge.bandwidth;
            if (load >= 1) {
                load = 1.0;
            }
            edge.weight = load;
        }
    }
    public void updateEdgeDelay(Path path) {
        for (Edge edge : path.edges()) {
            edge.weight = path.cost();
        }
    }

    public double linkUtil(Path path) {
        double total = 0.0;
        for (Edge edge : path.edges) {
            total = total + (double)edge.getLoadbandwidth()/edge.bandwidth;
        }
        return total;
    }

    public double linkUtil(Set<Edge> edges) {
        double total = 0.0;
        for (Edge edge : edges) {
            total = total + (double)edge.getLoadbandwidth()/edge.bandwidth;
        }
        return total;
    }

    public void printPath(Set<Path> paths) {
        for (Path path : paths) {
            for (Edge edge : path.edges()) {
                System.out.print(edge.src().v + ","+edge.dst().v);
            }
            System.out.println();
        }
    }
}
