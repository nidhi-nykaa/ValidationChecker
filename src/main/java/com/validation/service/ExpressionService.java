package com.validation.service;

import com.validation.dto.Filter;

import java.util.List;
import java.util.Map;

public interface ExpressionService {
    String generateExpression(List<Filter> filters);
    Object eval(String expression, Map<String, String> attributes);
}
