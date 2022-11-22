package model.statements;

import exceptions.MyException;
import model.ProgramState;

public interface IStatement {
    ProgramState execute(ProgramState state) throws MyException;

    public IStatement createDeepCopy();
}