package by.barilo.algorithm.prim.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class Edge {

    private Vertex from;

    private Vertex to;

    private int distance;
}
