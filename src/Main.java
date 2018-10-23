import java.util.*;

public class Main {
    public static  void main (String[] args) {

        Vertex v1 = new Vertex("v1");
        Vertex v2 = new Vertex("v2");
        Vertex v3 = new Vertex("v3");
        Vertex v4 = new Vertex("v4");

        Vertex v5 = new Vertex("v5");
        Vertex v6 = new Vertex("v6");
        Vertex v7 = new Vertex("v7");
        Vertex v8 = new Vertex("v8");

        Vertex v9 = new Vertex("v9");
        Vertex v10 = new Vertex("v10");
        Vertex v11 = new Vertex("v11");
        Vertex v12 = new Vertex("v12");


        Set<Vertex> vertices = Set.of(v1, v2, v3, v4, v5, v6, v7, v8);

//        Set<Edge> edges = Set.of(
//                new Edge(v1, v6, 1),
//                new Edge(v1, v5, 1),
//                new Edge(v1, v3, 1),
//                new Edge(v2, v3, 1),
//                new Edge(v3, v4, 1),
//                new Edge(v4, v6, 1),
//                new Edge(v5, v6, 1),
//                new Edge(v5, v4, 1));
        Set<Edge> edges = Set.of(
                new Edge(v1, v2, 0.0),
                new Edge(v2, v1, 0.0),
                new Edge(v2, v3, 0.0),
                new Edge(v1, v4, 0.0),
                new Edge(v3, v4, 0.0),
                new Edge(v4, v3, 0.0),
                new Edge(v5, v6, 0.0),
                new Edge(v6, v5, 0.0),
                new Edge(v6, v7, 0.0),
                new Edge(v5, v8, 0.0),
                new Edge(v7, v8, 0.0),
                new Edge(v8, v7, 0.0),
                new Edge(v3, v5, 0.0),
                new Edge(v4, v6, 0.0));


        Util util = new Util();

        Graph graph1 = new Graph(vertices, edges);

        GraphSearch graphSearch1 = new GraphSearch();

        Flow flow1 = new Flow(v1, v8, 4);
        Flow flow2 = new Flow(v1, v8, 6);
        Flow flow3 = new Flow(v1, v8, 6);
        Flow flow4 = new Flow(v1, v8,6);
        Flow flow5 = new Flow(v1, v8,6);
        Flow flow6 = new Flow(v1, v8,8);
        Flow flow7 = new Flow(v1, v8,6);
        Flow flow8 = new Flow(v1, v8,8);
        Flow flow9 = new Flow(v1, v7,6);
        Flow flow10 = new Flow(v1, v7,6);

        List<Flow> flows = List.of(flow1,flow2,flow3,flow4,flow5, flow6, flow7, flow8, flow9, flow10);

        DefaultResult result1 = graphSearch1.internalSearch(flow1.getSrc(), flow1.getDst(), graph1,2);

        Set<Edge> edgeSet1 = new HashSet<>();

        util.printPath(result1.paths);

        int i = 0;
        for (Path path : result1.paths) {

            for (int j = 0; j < 4; j++) {
                util.updateEdgeLoad(flows.get(i * 4 + j), path);
            }
            edgeSet1.addAll(path.edges());
            i++;
            if (i >= 2) break;
        }

        DefaultResult result12 = graphSearch1.internalSearch(flow9.getSrc(), flow9.getDst(), graph1,2);

        util.printPath(result12.paths);

        int h = 0;
        for (Path path : result12.paths) {

            for (int j = 0; j < 1; j++) {
                util.updateEdgeLoad(flows.get(9 + j), path);
            }
            edgeSet1.addAll(path.edges());
            h++;
            if (h >= 2) break;

        }

        double utils = util.linkUtil(edgeSet1);
        System.out.print((double)utils / graph1.getEdgeNumber());


        Vertex s1 = new Vertex("s1");
        Vertex s2 = new Vertex("s2");
        Vertex s3 = new Vertex("s3");
        Vertex s4 = new Vertex("s4");

        Vertex s5 = new Vertex("s5");
        Vertex s6 = new Vertex("s6");
        Vertex s7 = new Vertex("s7");
        Vertex s8 = new Vertex("s8");

        Vertex s9 = new Vertex("s9");
        Vertex s10 = new Vertex("s10");
        Vertex s11 = new Vertex("s11");
        Vertex s12 = new Vertex("s12");


        Set<Vertex> vertices1 = Set.of(s1, s2, s3, s4, s5, s6, s7, s8);

        Set<Edge> edges1 = Set.of(
                new Edge(s1, s2, 0.0),
                new Edge(s2, s1, 0.0),
                new Edge(s2, s3, 0.0),
                new Edge(s1, s4, 0.0),
                new Edge(s3, s4, 0.0),
                new Edge(s4, s3, 0.0),
                new Edge(s5, s6, 0.0),
                new Edge(s6, s5, 0.0),
                new Edge(s6, s7, 0.0),
                new Edge(s5, s8, 0.0),
                new Edge(s7, s8, 0.0),
                new Edge(s8, s7, 0.0),
                new Edge(s3, s5, 0.0),
                new Edge(s4, s6, 0.0));

        Graph graph2 = new Graph(vertices1, edges1);
        GraphSearch graphSearch2 = new GraphSearch();
        Set<Edge> edgeSet2 = new HashSet<>();

        Flow flow11 = new Flow(s1, s8, 4);
        Flow flow12 = new Flow(s1, s8, 6);
        Flow flow13 = new Flow(s1, s8, 6);
        Flow flow14 = new Flow(s1, s8,6);
        Flow flow15 = new Flow(s1, s8,6);
        Flow flow16 = new Flow(s1, s8,8);
        Flow flow17 = new Flow(s1, s8,6);
        Flow flow18 = new Flow(s1, s8,8);
        Flow flow19 = new Flow(s1, s8,6);
        Flow flow110 = new Flow(s1, s8,6);

        List<Flow> flows1 = List.of(flow11,flow12,flow13,flow14, flow15,flow16, flow17, flow18, flow19, flow110);

        for (int m = 0; m < flows1.size(); m++) {
            DefaultResult result = graphSearch2.internalSearch(flows1.get(m).getSrc(), flows1.get(m).getDst(), graph2,2);
            util.printPath(result.paths);

            int j = 0;
            flows1.get(m).half();
            for (Path path: result.paths) {
                util.updateEdgeWeight(flows1.get(m), path);
                edgeSet2.addAll(path.edges());
                if (++j >= 2) {
                    break;
                }
            }
        }


        double utils1 = util.linkUtil(edgeSet2);
        System.out.println((double)utils1 / graph2.getEdgeNumber());
        String a = "a";
        String b = "bc";
        String c = "a" + b;
        String d = "a" + "bc";
        String e = "abc";
        String s = a + b;
        System.out.print((c == d) +"," + (d == e) + "," + (e == s));
    }
}
