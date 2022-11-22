package model.statements;

import exceptions.MyException;
import model.dataStructures.MyiList;
import model.expressions.ProgramExpression;
import model.ProgramState;
import model.values.Value;

public class PrintStatement implements IStatement {
    ProgramExpression expression;

    public PrintStatement(ProgramExpression _expression){
        expression = _expression;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyiList<Value> outputValues = state.getOutputValues();

        outputValues.add(expression.evaluate(state.getSymbolsTable()));

        state.setOutputValues(outputValues);

        return state;
    }

    public IStatement createDeepCopy() {
        return new PrintStatement(expression.createDeepCopy());
    }

    @Override
    public String toString(){
        return "print(" + expression.toString() + ")";
    }


}
