package codeforces.contest1252;

import java.util.*;

public class ProblemB {
    public static void main(String[] args) {

    }

    final Graph graph = new Graph();

    public List<String> approxVertexCover() {
        List<String> approxmvc = new ArrayList<String>(); // set to hold our min vertex cover

// collect the edges of the graph
        Queue<Edge> queue = new LinkedList<Edge>(graph.getEdges()); // set of all vertices
        Set<Edge> unCoveredEdges = new HashSet<>(graph.getEdges());

        while (!queue.isEmpty()) {
            Edge edge = queue.remove();

            if (unCoveredEdges.contains(edge)) {
                unCoveredEdges.remove(edge);
                String source = edge.getSource();
                String dest = edge.getDestination();
                approxmvc.add(source);
                approxmvc.add(dest);

                unCoveredEdges.removeIf(e -> e.getDestination().equals(source) || e.getDestination().equals(dest) ||
                        e.getSource().equals(dest) || e.getSource().equals(source));
            }
        }
        return approxmvc;
    }

    final String source = "";

    public Set<String> doDepthFirstSearchRecursive() {
        Set<String> visited = new LinkedHashSet<String>();
        List<Node> nodes = new ArrayList<Node>(graph.getAdjNodes().keySet());

        Node src = nodes.get(nodes.indexOf(new Node(source)));

        dfsRecursive(nodes, src, visited);

        return visited;
    }

    public void dfsRecursive(List<Node> nodes, Node src, Set<String> visited) {
        visited.add(src.getName());
        src.setVisited(true);

        for (Node node : graph.getAdjacentNodes(src.getName())) {
            if (!node.isVisited()) {
                dfsRecursive(nodes, node, visited);
            }
        }
    }


    public class Edge {

        private String source;
        private String destination;
        private double weight;

        public Edge(String source, String destination) {

            this.source = source;
            this.destination = destination;
        }

        public Edge(String source, String destination, double weight) {

            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        /**
         * @return the source
         */
        public String getSource() {
            return source;
        }

        /**
         * @param source the source to set
         */
        public void setSource(String source) {
            this.source = source;
        }

        /**
         * @return the destination
         */
        public String getDestination() {
            return destination;
        }

        /**
         * @param destination the destination to set
         */
        public void setDestination(String destination) {
            this.destination = destination;
        }

        /**
         * @return the weight
         */
        public double getWeight() {
            return weight;
        }

        /**
         * @param weight the weight to set
         */
        public void setWeight(double weight) {
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Edge))
                return false;
            Edge oedge = (Edge) o;
            boolean dir1 = this.source.equals(oedge.getSource()) && this.destination.equals(oedge.getDestination());
            boolean dir2 = this.source.equals(oedge.getDestination()) && this.destination.equals(oedge.getSource());
            boolean same = dir1 || dir2;

            return same;
        }

        @Override
        public final int hashCode() {

            int res = 1;

            res = 31 * res + ((source == null) ? 0 : source.hashCode());
            res = 31 * res + ((destination == null) ? 0 : destination.hashCode());
            //res = 31 * res + Double.hashCode(weight);

            return res;
        }

    }

    public class Node {

        private String name;
        private boolean visited;

        public Node(String name) {
            this.name = name;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }


        /**
         * @return the visited
         */
        public boolean isVisited() {
            return visited;
        }


        /**
         * @param visited the visited to set
         */
        public void setVisited(boolean visited) {
            this.visited = visited;
        }


        @Override
        public boolean equals(Object o) {
            if (o == this)
                return true;
            if (!(o instanceof Node))
                return false;
            Node onode = (Node) o;
            boolean same = this.getName().equals(onode.getName());

            return same;
        }

        @Override
        public final int hashCode() {

            int res = 1;

            res = 31 * res + ((name == null) ? 0 : name.hashCode());

            return res;
        }

    }

    public class Graph {

        private Map<Node, List<Node>> adjNodes;
        private List<Edge> edges;

        public Graph() {

            adjNodes = new HashMap<Node, List<Node>>();
            edges = new ArrayList<Edge>();
        }

        public void addNode(String name) {

            Node node = new Node(name);
            adjNodes.putIfAbsent(node, new ArrayList<>());
        }

        public void removeNode(String name) {

            Node node = new Node(name);
            adjNodes.values().stream().forEach(e -> e.remove(node));
            adjNodes.remove(node);
        }

        public void addEdge(String name1, String name2, double weight) {

            Node n1 = new Node(name1);
            Node n2 = new Node(name2);

            adjNodes.get(n1).add(n2);
            adjNodes.get(n2).add(n1);

            // add edges to a list
            edges.add(new Edge(name1, name2, weight));

        }

        public void removeEdge(String name1, String name2) {

            Node n1 = new Node(name1);
            Node n2 = new Node(name2);
            List<Node> adjN1 = adjNodes.get(n1);
            List<Node> adjN2 = adjNodes.get(n2);

            if (adjN1 != null)
                adjN1.remove(n2);
            if (adjN2 != null)
                adjN2.remove(n1);

            edges.remove(new Edge(name1, name2));

        }

        public List<Node> getAdjacentNodes(String name) {

            Node node = new Node(name);

            return adjNodes.get(node);
        }

        public Edge getEdge(String source, String dest) {

            Edge e = new Edge(source, dest);
            int index = edges.indexOf(e);

            return edges.get(index);

        }

        /**
         * @return the edges
         */
        public List<Edge> getEdges() {
            return edges;
        }

        public Set<Node> getNodes() {

            return adjNodes.keySet();
        }

        /**
         * @return the adjNodes
         */
        public Map<Node, List<Node>> getAdjNodes() {
            return adjNodes;
        }

        /**
         * @param adjNodes the adjNodes to set
         */
        public void setAdjNodes(Map<Node, List<Node>> adjNodes) {
            this.adjNodes = adjNodes;
        }


        public List<String> toString(List<Node> adjnodes) {

            List<String> tostring = new ArrayList<String>();

            for (int i = 0; i < adjnodes.size(); i++) {
                tostring.add(adjnodes.get(i).getName());
            }

            return tostring;
        }

    }
}
