package com.validation.controller;

import com.validation.dto.*;
import com.validation.service.ExpressionService;
import com.validation.service.impl.ExpressionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestController {

    @Autowired
    private ExpressionService expressionService;

    public static void main1(String[] args) {
        TestController testController = new TestController();
        Validations ob = new Validations("eq", "12.22");
        Validations ob1 = new Validations("neq", "null");
        Headers headers = new Headers("sku", String.class, ob1);
        Headers headers1 = new Headers("price", Double.class, ob);
        List<Headers> list = new ArrayList<>();
        list.add(headers);
        list.add(headers1);
        List<Value> inner = new ArrayList<>();
        List<DataValue> outer = new ArrayList<>();
        Value value = new Value("sku", "Nyk123");
        inner.add(value);
        Value value1 = new Value("price", 12.22);
        inner.add(value1);
        DataValue dataValue = new DataValue(inner);
        outer.add(dataValue);
        FileUploadConfig fileUploadConfig = new FileUploadConfig(list, outer);
        System.out.println(fileUploadConfig);

        for (DataValue dataValue1 : fileUploadConfig.getDataValues()) {
            for (Headers headers2 : fileUploadConfig.getHeaders()) {
                for (Value value2 : dataValue1.getValues()) {
                    if (value2.getKey().equalsIgnoreCase(headers2.getName())) {
                        Class classType = headers2.getDataType();
                        boolean res11 = classType.isInstance(value2.getVal());
                        System.out.println("res11 is " + res11);

                        if (null != headers2.getValidations()) {
                            Map<String, String> attr = new HashMap<>();
                            attr.put(headers2.getName(), String.valueOf(value2.getVal()));
                            // price, 12.22
                            System.out.println("other validations check..........");
                            Filter filter = new Filter(headers2.getName(), headers2.getValidations().getOperator(), headers2.getValidations().getVal());
                            List<Filter> filters = new ArrayList<>();
                            filters.add(filter);
                            ExpressionService expressionService = new ExpressionServiceImpl();
                            String parsedExpression = expressionService.generateExpression(filters);
                            System.out.println("parsedExpression is " + parsedExpression);
                            boolean b = (boolean) expressionService.eval(parsedExpression, attr);
                            System.out.println("b is " + b);
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Validations ob = new Validations("==", "12.22");
        Validations ob1 = new Validations("!=", "null");
        Headers headers = new Headers("sku", String.class, ob1);
        Headers headers1 = new Headers("price", Double.class, ob);
        List<Headers> list = new ArrayList<>();
        list.add(headers);
        list.add(headers1);
        List<Value> inner = new ArrayList<>();
        List<DataValue> outer = new ArrayList<>();
        Value value = new Value("sku", "Nyk12");
        inner.add(value);

        Value value1 = new Value("price", 12.22);
        inner.add(value1);

        DataValue dataValue = new DataValue(inner);
        outer.add(dataValue);
        FileUploadConfig fileUploadConfig = new FileUploadConfig(list, outer);
        //System.out.println(fileUploadConfig);
        ExpressionParser parser = new SpelExpressionParser();

        for (DataValue dataValue1 : fileUploadConfig.getDataValues()) {
            for (Headers headers2 : fileUploadConfig.getHeaders()) {
                for (Value value2 : dataValue1.getValues()) {
                    if (value2.getKey().equalsIgnoreCase(headers2.getName())) {

                        // dataType chk
                        String dataTypeExp = "#" + headers2.getName() + " instanceOf T(" + headers2.getDataType().getCanonicalName().replace("java.lang.", "") + ")";
                        System.out.println(dataTypeExp);
                        // Expression expression1 = parser.parseExpression("#sku instanceOf T(String)");
                        Expression exp1 = parser.parseExpression(dataTypeExp);
                        StandardEvaluationContext  stContext1 = new StandardEvaluationContext();
                        stContext1.setVariable(value2.getKey(), value2.getVal());
                        System.out.println(exp1.getValue(stContext1, Boolean.class));

                        // validations chk such as mandatorty field chk and some other relational chks
                        String expression = "#" + headers2.getName() + headers2.getValidations().getOperator() + " " + headers2.getValidations().getVal() ;
                        System.out.println(expression);
                        Expression exp = parser.parseExpression(expression);
                        StandardEvaluationContext  stContext = new StandardEvaluationContext();
                        stContext.setVariable(value2.getKey(), value2.getVal());
                        System.out.println(exp.getValue(stContext, Boolean.class));
                    }
                }
            }
        }
    }

    public static void main2(String[] args) {
        User user = new User(10, true);
        App app = new App("fb", true);
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("user", user);
        context.setVariable("app", app);

        ExpressionParser parser = new SpelExpressionParser();
        Expression exp1 = parser.parseExpression("#user.isAdmin() and #app.isActive()");
        boolean result = exp1.getValue(context,Boolean.class);
        System.out.println(result);

        Expression exp = parser.parseExpression("age > 18");
        System.out.println(exp.getValue(user,Boolean.class));
        exp = parser.parseExpression("age < 18 and isAdmin()");
        System.out.println(exp.getValue(user,Boolean.class));

        Validations ob = new Validations("eq", "12.22");
        Validations ob1 = new Validations("neq", "null");
        Headers headers = new Headers("sku", String.class, ob1);
        Headers headers1 = new Headers("price", Double.class, ob);
        List<Headers> list = new ArrayList<>();
        list.add(headers);
        list.add(headers1);
        List<Value> inner = new ArrayList<>();
        List<DataValue> outer = new ArrayList<>();
        Value value = new Value("sku", "Nyk123");
        inner.add(value);
        Value value1 = new Value("price", 12.22);
        inner.add(value1);
        DataValue dataValue = new DataValue(inner);
        outer.add(dataValue);
        FileUploadConfig fileUploadConfig = new FileUploadConfig(list, outer);
        System.out.println(fileUploadConfig);

        Expression exp2 = parser.parseExpression("#price > 18.4");
        Expression exp3 = parser.parseExpression("#sku != null");
        Expression exp4 = parser.parseExpression("#sku instanceOf T(String)");
        StandardEvaluationContext  stContext  = new StandardEvaluationContext();
        stContext.setVariable("price", 22);
        StandardEvaluationContext  stContext1  = new StandardEvaluationContext();
        stContext1.setVariable("sku", "Nyk123");
        System.out.println(exp2.getValue(stContext, Boolean.class));
        System.out.println(exp3.getValue(stContext1, Boolean.class));
        System.out.println(exp4.getValue(stContext1, Boolean.class));
    }
}

