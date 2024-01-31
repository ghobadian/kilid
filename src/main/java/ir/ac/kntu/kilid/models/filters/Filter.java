package ir.ac.kntu.kilid.models.filters;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Filter {
    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values;//Used in case of IN operator
}
