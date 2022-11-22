package model.expressions;

import exceptions.MyException;
import model.dataStructures.MyiDictionary;
import model.values.Value;

public interface ProgramExpression {
    Value evaluate(MyiDictionary<String, Value> table) throws MyException;

    ProgramExpression createDeepCopy();
}
