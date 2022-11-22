package controller;

import exceptions.MyException;
import model.dataStructures.MyiStack;
import model.ProgramState;
import model.statements.IStatement;
import repository.IRepository;

import java.io.IOException;

public class Controller {

    IRepository repository;
    boolean displayFlag = false;

    public Controller(IRepository _repository, boolean _displayFlag){
        this.repository = _repository;
        this.displayFlag = _displayFlag;
    }

    public boolean getDisplayFlag(){
        return displayFlag;
    }

    public void setDisplayFlag(boolean newDisplayFlag){
        displayFlag = newDisplayFlag;
    }

    public ProgramState oneStepExecution(ProgramState state) throws MyException{
        MyiStack<IStatement> executionStack = state.getExecutionStack();
        if(executionStack.isEmpty())
            throw new MyException("ProgramState's stack is empty!");

        IStatement currentStatement = executionStack.pop();

        state.setExecutionStack(executionStack);

        return currentStatement.execute(state);
    }

    public void allStepExecution() throws IOException {
        ProgramState programState = repository.getCurrentProgramState();
        displayCurrentProgramState();

        repository.logProgramStateExecution();
        while (!programState.getExecutionStack().isEmpty()){
            oneStepExecution(programState);
            displayCurrentProgramState();
            repository.logProgramStateExecution();
        }

        programState.resetToOriginalProgram();
    }

    public void displayCurrentProgramState(){
        if(displayFlag)
            System.out.println(repository.getCurrentProgramState().toString());
    }

}
