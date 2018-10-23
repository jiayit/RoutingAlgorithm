import java.util.Objects;

public class Vertex {
    String v;
    public Vertex(String v) {
        this.v = v;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Vertex) {
            return Objects.equals(this.v, ((Vertex) obj).v);
        }
        return false;
    }
}
