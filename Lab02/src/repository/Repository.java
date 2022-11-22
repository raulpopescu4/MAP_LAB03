package repository;

import exceptions.MyException;
import model.ProgramState;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Repository implements IRepository {

    ProgramState programState;
    String logFilePath;

    public Repository(ProgramState _programState, String _logFilePath){
        programState = _programState; logFilePath = _logFilePath;
    }

    public ProgramState getCurrentProgramState() {
        return programState;
    }

    public String getLogFilePath(){
        return logFilePath;
    }

    void setLogFilePath(String newLogFilePath){
        logFilePath = newLogFilePath;
    }


    public void logProgramStateExecution() throws MyException , IOException {
        PrintWriter logFile = new PrintWriter(new BufferedWriter(new FileWriter(logFilePath, true)));

        logFile.println(this.programState.toString());
        logFile.close();
    }

    public void setProgramState(ProgramState newProgramState){
        programState = newProgramState;
    }
}
