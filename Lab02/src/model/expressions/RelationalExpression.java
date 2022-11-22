package model.expressions;

import exceptions.MyException;
import model.dataStructures.MyiDictionary;
import model.types.IntType;
import model.values.BoolValue;
import model.values.IntValue;
import model.values.Value;

public class RelationalExpression implements ProgramExpression{
    ProgramExpression firstExpression;
    ProgramExpression secondExpression;
    String relation;

    public RelationalExpression(String _relation, ProgramExpression _firstExpression, ProgramExpression _secondExpression){
        firstExpression = _firstExpression;
        secondExpression = _secondExpression;
        relation = _relation;
    }


    public Value evaluate(MyiDictionary<String, Value> table) throws MyException {
        Value firstValue, secondValue;
        firstValue = firstExpression.evaluate(table);

        if(firstValue.getType().equals(new IntType())){
            secondValue = secondExpression.evaluate(table);

            if(secondValue.getType().equals(new IntType())){
                IntValue firstInteger = (IntValue) firstValue;
                IntValue secondInteger = (IntValue) secondValue;

                int firstNumber, secondNumber;

                firstNumber = firstInteger.getValue();
                secondNumber = secondInteger.getValue();

                return switch (relation){
                    case "<" -> new BoolValue(firstNumber < secondNumber);

                    case "<=" -> new BoolValue(firstNumber <= secondNumber);

                    case "==" -> new BoolValue(firstNumber == secondNumber);

                    case "!=" -> new BoolValue(firstNumber != secondNumber);

                    case ">" -> new BoolValue(firstNumber > secondNumber);

                    case ">=" -> new BoolValue(firstNumber >= secondNumber);

                    default -> throw new MyException("Not a valid relation provided!");
                };
            } else
                throw new MyException("Second operand is not an integer!");
        } else
            throw new MyException("First operand is not an integer!");
    }

    public ProgramExpression createDeepCopy() {
        return new RelationalExpression(relation, firstExpression.createDeepCopy(), secondExpression.createDeepCopy());
    }

    @Override
    public  String toString(){
        return firstExpression.toString() + " " + relation + " " + secondExpression.toString();
    }
}
