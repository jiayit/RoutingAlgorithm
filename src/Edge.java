import java.util.Objects;

public class Edge {

    Vertex src;
    Vertex dst;
    double weight;
    int bandwidth = 10;
    int loadbandwidth = 0;

    public Edge (Vertex src, Vertex dst, double weight) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
    }

    public Edge (Vertex src, Vertex dst, double weight, int bandwidth) {
        this.src = src;
        this.dst = dst;
        this.weight = weight;
        this.bandwidth = bandwidth;
    }

    public void setLoadbandwidth(int load) {
        this.loadbandwidth = load;
    }

    public void updateLoadbandwidth(int load) {
        int total = load + this.loadbandwidth;
        if (total >= 10) total = 10;
        this.loadbandwidth = total;
    }

    public int getLoadbandwidth() {
        return this.loadbandwidth;
    }
    public Vertex src() {
        return src;
    }

    public Vertex dst() {
        return dst;
    }

    public int hashCode() {
        return Objects.hash(src, dst);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof  Edge) {
            return Objects.equals(this.src, ((Edge) obj).src)
                    && Objects.equals(this.dst, ((Edge) obj).dst);
        }
        return false;
    }
}
