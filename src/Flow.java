public class Flow {
    Vertex src;
    Vertex dst;
    int loadSpeed;
    public Flow(Vertex src, Vertex dst, int loadSpeed) {
        this.src = src;
        this.dst = dst;
        this.loadSpeed = loadSpeed;
    }

    public void half() {
        loadSpeed = loadSpeed / 2;
    }
    public Vertex getDst() {
        return dst;
    }

    public Vertex getSrc() {
        return src;
    }

    public int getLoadSpeed() {
        return loadSpeed;
    }
}
