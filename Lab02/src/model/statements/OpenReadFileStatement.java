package model.statements;

import exceptions.MyException;
import model.ProgramState;
import model.dataStructures.MyiDictionary;
import model.expressions.ProgramExpression;
import model.types.StringType;
import model.values.StringValue;
import model.values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements IStatement{

    ProgramExpression expression;

    public OpenReadFileStatement(ProgramExpression _expression){
        expression =_expression;
    }

    public ProgramState execute(ProgramState state) throws MyException {
        Value value = expression.evaluate(state.getSymbolsTable());

        if(!value.getType().equals(new StringType()))
            throw new MyException("The expression must be a StringType!");

        StringValue fileName = (StringValue) value;

        if(state.getFileTable().isDefined(fileName.getValue()))
            throw new MyException("The file is already opened!");

        BufferedReader bufferedReader;

        try{
            bufferedReader = new BufferedReader(new FileReader(fileName.getValue()));
        } catch (FileNotFoundException exception){
            throw new MyException(fileName + " file could not be opened! ");
        }

        MyiDictionary<String, BufferedReader> fileTable = state.getFileTable();
        fileTable.addSymbol(fileName.getValue(), bufferedReader);
        state.setFileTable(fileTable);

        return state;

    }

    public IStatement createDeepCopy(){
        return new OpenReadFileStatement(expression.createDeepCopy());
    }

    @Override
    public String toString(){
        return "OpenReadFile(" + expression.toString() + ")";
    }


}
