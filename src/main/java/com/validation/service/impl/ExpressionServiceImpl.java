package com.validation.service.impl;

import com.validation.dto.Filter;
import com.validation.service.ExpressionService;
import com.validation.utils.ExpressionBuilder;
import org.mvel2.MVEL;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExpressionServiceImpl implements ExpressionService {

    @Override
    public String generateExpression(List<Filter> filters) {
        ExpressionBuilder eb = new ExpressionBuilder();
        for (Filter f : filters) {
            eb.add(f);
        }
        return eb.build();
    }

    @Override
    public Object eval(String expression, Map<String, String> attributes) {
        // Compile the expr if needed
        Serializable compiledExpression = MVEL.compileExpression(expression);
        Object result = MVEL.executeExpression(compiledExpression, attributes);
        System.out.println("ExpressionService returning result {}"+ result);
        return result;
    }
}
