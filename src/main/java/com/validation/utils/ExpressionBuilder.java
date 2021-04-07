package com.validation.utils;

import com.validation.dto.Filter;
import org.springframework.util.StringUtils;

public class ExpressionBuilder {

    private StringBuilder expression;

    public ExpressionBuilder() {
        expression = new StringBuilder();
    }

    public String build() {
        return expression.toString();
    }

    public ExpressionBuilder add(Filter filter) {
        expression.append(filter.getFilter());
        return this;
    }

    public ExpressionBuilder and() {
        if (!StringUtils.isEmpty(expression)) {
            appendAndOperator();
        }
        return this;
    }

    private void appendAndOperator() {
        expression.append(" ");
        expression.append(Filter.Operator.AND.getStringOperator());
        expression.append(" ");
    }
}
