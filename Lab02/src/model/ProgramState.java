package model;

import model.dataStructures.*;
import model.statements.IStatement;
import model.values.Value;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public class ProgramState {
    MyiStack<IStatement> executionStack;
    MyiList<Value> outputValues;
    MyiDictionary<String, Value> symbolsTable;
    MyiDictionary<String, BufferedReader> fileTable;

    IStatement originalProgram;

    public ProgramState(IStatement _program){
        executionStack = new MyStack<>();
        symbolsTable = new MyDictionary<>();
        outputValues = new MyList<>();
        fileTable = new MyDictionary<>();

        originalProgram = _program.createDeepCopy();

        executionStack.push(originalProgram);
    }

    public MyiStack<IStatement> getExecutionStack() {
        return executionStack;
    }

    public MyiList<Value> getOutputValues(){
        return outputValues;
    }


    public MyiDictionary<String, Value> getSymbolsTable() {
        return symbolsTable;
    }

    public void setOutputValues(MyiList<Value> newOutputValues) {
        outputValues = newOutputValues;
    }

    public void setExecutionStack(MyiStack<IStatement> newExecutionStack) {
        executionStack = newExecutionStack;
    }

    public void setSymbolsTable(MyiDictionary<String, Value> newSymbolsTable) {
        symbolsTable = newSymbolsTable;
    }


    public MyiDictionary<String, BufferedReader> getFileTable(){ return fileTable;}

    public void setFileTable(MyiDictionary<String, BufferedReader> newFileTable){ fileTable = newFileTable;}

    public void resetToOriginalProgram() {
        executionStack = new MyStack<>();
        symbolsTable = new MyDictionary<>();
        outputValues = new MyList<>();

        executionStack.push(originalProgram.createDeepCopy());
    }

    @Override
    public String toString(){
        return "Execution stack: \n" + executionStack.toString() + "\nSymbols table: \n" + symbolsTable.toString() + "\nOutput values: \n" + outputValues.toString() + "\nFile table: \n" + fileTable.toString();
    }
}
