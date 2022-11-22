package model.expressions;


import exceptions.MyException;
import model.dataStructures.MyiDictionary;
import model.types.IntType;
import model.values.IntValue;
import model.values.Value;

public class ArithmeticExpression implements ProgramExpression{
    ProgramExpression e1;
    ProgramExpression e2;
    char operation; //1-plus, 2-minus, 3-star, 4-divide

    public ArithmeticExpression(char _operation, ProgramExpression _e1, ProgramExpression _e2){
        e1 = _e1;
        e2 = _e2;
        operation = _operation;
    }

    public Value evaluate(MyiDictionary<String, Value> table) throws MyException{
        Value v1, v2;
        v1 = e1.evaluate(table);

        if(v1.getType().equals(new IntType())){
            v2 = e2.evaluate(table);
                    if(v2.getType().equals(new IntType())){
                        IntValue i1 = (IntValue) v1;
                        IntValue i2 = (IntValue) v2;
                        int n1, n2;
                        n1 = i1.getValue();
                        n2 = i2.getValue();

                        if(operation == '+') return new IntValue(n1 + n2);
                        if(operation == '-') return new IntValue(n1 - n2);
                        if(operation == '*') return new IntValue(n1 * n2);
                        if(operation == '/')
                            if(n2 == 0) throw new MyException("division by zero");
                                else return new IntValue(n1/n2);

                    }
                    else
                        throw new MyException("second operand is not an integer");
        }
        else throw new MyException("first operand is not an integer");

        return null;

    }

    public ProgramExpression createDeepCopy() {
        return new ArithmeticExpression(operation, e1, e2);
    }

    @Override
    public String toString(){
        return e1.toString() + " " + operation + " " + e2.toString();
    }
}
