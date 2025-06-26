package by.barilo.algorithm.prim;

import by.barilo.algorithm.prim.dto.Edge;
import by.barilo.algorithm.prim.dto.Vertex;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class Application {

    public static void main(String[] args) {
        Condition condition = getCondition();
        Set<Vertex> vertices = condition.getVertices();
        List<Edge> edges = condition.getEdges();
        List<Edge> result = new ArrayList<>();

        Set<Vertex> connectedVertices = new HashSet<>();
        Vertex vertex = vertices.stream().findFirst().get();
        Edge closest = null;
        for (Edge edge : edges) {
            if (edge.getFrom().equals(vertex) || edge.getTo().equals(vertex)) {
                if (Objects.isNull(closest) || edge.getDistance() < closest.getDistance()) {
                    closest = edge;
                }
            }
        }
        result.add(closest);
        connectedVertices.add(closest.getFrom());
        connectedVertices.add(closest.getTo());
        while (connectedVertices.size() != vertices.size()) {
            Set<Vertex> connected = result.stream()
                    .map(edge -> List.of(edge.getFrom(), edge.getTo()))
                    .flatMap(List::stream)
                    .collect(Collectors.toSet());
            Edge closestEdge = edges.stream()
                    .filter(edge -> !result.contains(edge))
                    .filter(edge -> connected.contains(edge.getFrom()) || connected.contains(edge.getTo()))
                    .min(Comparator.comparing(Edge::getDistance)).orElseThrow();
            result.add(closestEdge);
            connectedVertices.add(closestEdge.getTo());
            connectedVertices.add(closestEdge.getFrom());
        }

        for (Edge edge : result) {
            System.out.println(edge.toString());
        }
    }

    public static Condition getCondition() {
        Vertex vertexA = Vertex.builder().name("A").build();
        Vertex vertexB = Vertex.builder().name("B").build();
        Vertex vertexC = Vertex.builder().name("C").build();
        Vertex vertexD = Vertex.builder().name("D").build();
        Vertex vertexE = Vertex.builder().name("E").build();
        Vertex vertexF = Vertex.builder().name("F").build();
        Vertex vertexG = Vertex.builder().name("G").build();
        Set<Vertex> vertices = new LinkedHashSet<>();
        vertices.add(vertexA);
        vertices.add(vertexB);
        vertices.add(vertexC);
        vertices.add(vertexD);
        vertices.add(vertexE);
        vertices.add(vertexF);
        vertices.add(vertexG);

        List<Edge> edges = new ArrayList<>();
        edges.add(Edge.builder().from(vertexA).to(vertexB).distance(3).build());
        edges.add(Edge.builder().from(vertexB).to(vertexC).distance(3).build());
        edges.add(Edge.builder().from(vertexC).to(vertexD).distance(3).build());
        edges.add(Edge.builder().from(vertexD).to(vertexE).distance(5).build());
        edges.add(Edge.builder().from(vertexE).to(vertexF).distance(4).build());
        edges.add(Edge.builder().from(vertexF).to(vertexA).distance(2).build());
        edges.add(Edge.builder().from(vertexB).to(vertexG).distance(6).build());
        edges.add(Edge.builder().from(vertexC).to(vertexG).distance(1).build());
        edges.add(Edge.builder().from(vertexF).to(vertexG).distance(3).build());
        edges.add(Edge.builder().from(vertexE).to(vertexG).distance(3).build());
        edges.add(Edge.builder().from(vertexC).to(vertexE).distance(2).build());

        Condition condition = new Condition();
        condition.setEdges(edges);
        condition.setVertices(vertices);
        return condition;
    }

    @Data
    public static class Condition {
        private Set<Vertex> vertices;

        private List<Edge> edges;
    }
}
