import * as React from "react";
import { Interfaces } from "./interfaces";
import { Game } from "./Game"

export namespace MsgHandler {
    // var typeToFct: { [key: string]: (msgInfo: any, game: Interfaces.IGame) => void; } = {
    //     "FieldMessage": setField, "NewGameMsg": newGame, "OpponentsConnectedMsg": setOpponent, "FullMsg": fullGame, "GameOver": gameOver
    // };

    // export function handle(msgInfo: string, game: Interfaces.IGame): boolean {
    //     console.log("Handle msg" + msgInfo)
    //     let obj: Interfaces.IMsgInfo = JSON.parse(msgInfo);
    //     var handleFunc = typeToFct[obj.type];
    //     var msg = obj.message
    //     if (handleFunc !== undefined) {
    //         handleFunc(msg, game);
    //         return true;
    //     } else {
    //         console.log("Unhandled type: " + obj.type);
    //     }
    //     return false;
    // }

    export function setField(msg: Interfaces.IFieldMessage, game: Interfaces.IGame) {
        console.log("Set Field " + msg.field + " for player " + msg.player);
        game.setOneField(msg.field, msg.player);
        game.status = "Player " + game.getNext(msg.player) + " is on the turn!";
        console.log("Board " + game.board);
    }

    export function newGame(msg: Interfaces.INewGame, game: Interfaces.IGame) {
        console.log("Set New Game");
        game.player = msg.player;
        game.status = "Player X is on the turn!";
        game.startNewGame(msg.player);
    }
    export function setOpponent(msg: Interfaces.INewOpponent, game: Interfaces.IGame) {
        console.log("Found opponent u r player " + msg.symbol);
        game.player = msg.symbol
        game.started = true
        game.status = "Player X is on the turn!";
    }
    // export function wonGame(msg: Interfaces.IWonMsg, game: Interfaces.IGame) {
    //     console.log("Won Game player " + msg.winner + " row " + msg.winRow);
    //     game.winrow = msg.winRow;
    //     game.finished = true;
    //     if (msg.winner === game.player) {
    //         game.status = "You won";
    //     } else {
    //         game.status = "You lose";
    //     }
    // }

    export function gameOver(msg: Interfaces.IGameOver, game: Interfaces.IGame) {
        console.log("GameOver with the status " + msg.winner + ", " + msg.winnerRow + ", " + msg.full);
        game.finished = true;
        if (msg.winner) {
            game.winrow = msg.winnerRow;
            game.status = msg.winner === game.player? "You won" : "You lose"
        } else {
            game.status = "Game is draw";
        }
    }

    // export function fullGame(msg: string, game: Interfaces.IGame) {
    //     console.log("Board is full");
    //     game.status = "Game is draw";
    //     game.finished = true;
    // }

    export function setErrorMessage(msg: Interfaces.IError, game: Interfaces.IGame){
        game.error = msg.error;
    }

    export function setGame(msg: Interfaces.ILoadGame, game: Interfaces.IGame){
        if (msg.loadGame) {
            game.loadGame(msg.board, msg.player, msg.opponentName, msg.turn)
        }
    }
}