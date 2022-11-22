package repository;

import exceptions.MyException;
import model.ProgramState;

import java.io.IOException;

public interface IRepository {
    ProgramState getCurrentProgramState();

    String getLogFilePath();

    void logProgramStateExecution() throws MyException, IOException;

}
