package com.example.javasudoku.buildlogic;

import com.example.javasudoku.computationlogic.GameLogic;
import com.example.javasudoku.persistence.LocalStorageImpl;
import com.example.javasudoku.problemdomain.IStorage;
import com.example.javasudoku.problemdomain.SudokuGame;
import com.example.javasudoku.userinterface.IUserInterfaceContract;
import com.example.javasudoku.userinterface.logic.ControlLogic;

import java.io.IOException;

public class SudokuBuildLogic {
    public static void build(IUserInterfaceContract.View userInterface) throws IOException {
        SudokuGame initialState;
        IStorage storage = new LocalStorageImpl();

        try {
            initialState = storage.getGameData();
        } catch (IOException e){
            initialState = GameLogic.getNewGame();
            storage.updateGameData(initialState);
        }

        IUserInterfaceContract.EventListener uiLogic = new ControlLogic(storage, userInterface);

        userInterface.setListener(uiLogic);
        userInterface.updateBoard(initialState);
    }
}
