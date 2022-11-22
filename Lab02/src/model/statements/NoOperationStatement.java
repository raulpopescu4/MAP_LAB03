package model.statements;

import exceptions.MyException;
import model.ProgramState;

public class NoOperationStatement implements IStatement {

    public ProgramState execute(ProgramState state) throws MyException {
        return null;
    }

    public IStatement createDeepCopy() {
        return new NoOperationStatement();
    }

    @Override
    public String toString(){
        return "NoOperation Statement";
    }
}
