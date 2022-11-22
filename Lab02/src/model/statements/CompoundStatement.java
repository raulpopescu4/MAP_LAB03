package model.statements;

import exceptions.MyException;
import model.dataStructures.MyiStack;
import model.ProgramState;

public class CompoundStatement implements IStatement {
    IStatement first;
    IStatement second;

    public CompoundStatement(IStatement _first, IStatement _second){
        first = _first;
        second = _second;
    }

    public ProgramState execute(ProgramState state) throws MyException{
        MyiStack<IStatement> statementStack = state.getExecutionStack();
        statementStack.push(second);
        statementStack.push(first);
        return state;
    }

    public IStatement createDeepCopy() {
        return new CompoundStatement(first.createDeepCopy(), second.createDeepCopy());
    }

    @Override
    public String toString(){
        return "(" + first.toString() + ";" + second.toString() + ")";
    }




}
