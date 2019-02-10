package com.uns.ftn.sciencejournal.service.utils;

import java.util.Objects;

public class BoolOperator {

    public enum Operator {
        AND, OR;

        private String expression;

        static {
            OR.expression = " || ";
            AND.expression = " && ";
        }

        public String getExpression() {
            return expression;
        }
    }

    private Operator operator;
    private Integer index;

    public BoolOperator() {
    }

    public BoolOperator(Operator operator, Integer index) {
        this.operator = operator;
        this.index = index;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoolOperator that = (BoolOperator) o;
        return operator == that.operator;
    }
}
