package com.validation.dto;

import java.util.*;

public class Filter {
    private String           field;
    private String           operator;
    private Object           value;

    public Filter(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public String getFilter() {
        Operator op = Operator.get(this.operator);
        if (op == null) {
            return null;
        }
        if (this.getField() == null && this.getValue() == null) {
            return " " + op.getStringOperator() + " ";
        }
        StringBuilder sb = new StringBuilder();
        if (this.value != null && this.value instanceof String) {
            String value = "\"" + this.value + "\"";
            if ((op == Operator.EQ || op == Operator.NEQ)) {
                if (op == Operator.NEQ) {
                    sb.append("!");
                    sb.append("(");
                }
                sb.append(value);
                sb.append(".equals(");
                sb.append(this.getField());
                sb.append(")");
                if (op == Operator.NEQ) {
                    sb.append(")");
                }
            }
            if (op == Operator.LIKE || op == Operator.NOT_LIKE) {
                sb.append("(" + this.getField() + " != null && ");
                sb.append(this.getField()+".toLowerCase()");
                sb.append(".indexOf(");
                sb.append(value+".toLowerCase()");
                sb.append(")");
                if (op == Operator.LIKE) {
                    sb.append(" != -1");
                } else {
                    sb.append(" == -1");
                }
                sb.append(")");
            }
            if (op == Operator.STARTS_WITH) {
                sb.append("(" + this.getField() + " != null && ");
                sb.append(this.getField());
                sb.append(".startsWith(");
                sb.append(value);
                sb.append(") == true");
                sb.append(")");
            }
            if (op == Operator.CONTAINS) {
                sb.append("(");
                sb.append(this.getField());
                sb.append(" != null && ");
                sb.append(this.getField());
                sb.append(".contains(").append(value).append(")");
                sb.append(")");
            }
        } /*else {
            sb.append(this.getField());
            sb.append(op.getStringOperator());
            sb.append(this.getValue());
        }*/
        return sb.toString();
    }

    public Object getValue() {
        return value;
    }

    public String getField() {
        return field;
    }

    public enum Operator {
        STARTS_WITH("startsWith", "startsWith", "sw"),
        LIKE("indexOf", "like"),
        NOT_LIKE("indexOf", "notlike"),
        EQ("==", "eq", "EQ", "=="),
        NEQ("!=", "neq", "NEQ", "!="),
        GT(">", "gt", "GT", ">"),
        LT("<", "lt", "LT", "<"),
        GTE(">=", "gte", "eqgt", "GTE", ">="),
        LTE("<=", "lte", "eqlt", "LTE", "<="),
        AND("&&", "AND", "and", "&&"),
        OR("||", "OR", "or", "||"),
        SLP("(", "SLP", "slp", "("),
        SRP(")", "SRP", "srp", ")"),/*, IN("eq","EQ","==")*/
        CONTAINS("contains","contains"); /*Used for List<String>*/

        private static final Map<String, Operator> lookup = new HashMap<>();

        static {
            for (Operator s : EnumSet.allOf(Operator.class)) {
                for (String v : s.values) {
                    lookup.put(v, s);
                }
            }
        }

        private List<String> values;
        private String       stringOperator;

        private Operator(String stringOperator, String... values) {
            this.stringOperator = stringOperator;
            this.values = Arrays.asList(values);
        }

        public static Operator get(String value) {
            return lookup.get(value);
        }

        public String getStringOperator() {
            return stringOperator;
        }
    }


    @Override
    public String toString() {
        return "Filter{" +
                "field='" + field + '\'' +
                ", operator='" + operator + '\'' +
                ", value=" + value +
                '}';
    }
}
