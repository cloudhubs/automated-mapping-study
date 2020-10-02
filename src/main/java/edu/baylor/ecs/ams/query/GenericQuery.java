package edu.baylor.ecs.ams.model.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GenericQuery {
    private List<GenericQuery> nestedQueries;
    private List<QueryLiteral> literals;
    private List<QueryConnector> connectors;

    // Parse query string into generic query object
    public GenericQuery(String query) {
        
    }
}
