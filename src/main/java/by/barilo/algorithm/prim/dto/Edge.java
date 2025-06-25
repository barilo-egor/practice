package by.barilo.algorithm.prim.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Edge {

    private Vertex from;

    private Vertex to;

    private int distance;
}
