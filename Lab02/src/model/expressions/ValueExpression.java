package model.expressions;

import exceptions.MyException;
import model.dataStructures.MyiDictionary;
import model.values.Value;

public class ValueExpression implements ProgramExpression{
    Value expression;

    public ValueExpression(Value _expression){
        expression =_expression;
    }

    public Value evaluate(MyiDictionary<String, Value> table) throws MyException{
        return expression;
    }

    public ProgramExpression createDeepCopy() {
        return new ValueExpression(expression);
    }

    @Override
    public String toString(){
        return expression.toString();
    }
}
