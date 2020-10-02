package edu.baylor.ecs.ams.model.query;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QueryLiteral {
    private QueryScope scope;
    private String phrase;
}
