package model.statements;

import exceptions.MyException;
import model.dataStructures.MyiDictionary;
import model.dataStructures.MyiStack;
import model.expressions.ProgramExpression;
import model.ProgramState;
import model.types.Type;
import model.values.Value;

public class AssignStatement implements IStatement {
    String id;
    ProgramExpression expression;

    public AssignStatement(String _id, ProgramExpression _expression){
        id = _id;
        expression = _expression;

    }

    public ProgramState execute(ProgramState state) throws MyException {
        MyiStack<IStatement> stack = state.getExecutionStack();
        MyiDictionary<String, Value> symbolTable = state.getSymbolsTable();

        if (symbolTable.isDefined(id)){
            Value value = expression.evaluate(symbolTable);
            Type idType = (symbolTable.lookUp(id).getType());
            if(value.getType().equals(idType))
                symbolTable.update(id, value);
            else throw new MyException("declared type of variable" + id + " and type of the assigned expression do not match");


        }
        else throw new MyException("the used variable" + id + "was not declared before");

        return state;
    }

    public IStatement createDeepCopy() {
        return new AssignStatement(id, expression.createDeepCopy());
    }

    @Override
    public String toString(){
        return id + " = " + expression.toString();
    }


}
