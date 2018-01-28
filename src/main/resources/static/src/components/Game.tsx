import * as React from "react";
import { Interfaces } from "./interfaces";

export class Game implements Interfaces.IGame {
    private _board: string[] = new Array(9);
    private _turn: string = "x";
    private _finished: boolean = false;
    private _winrow: number[] = new Array(3);
    private _player: string;
    private _started: boolean;
    private _status: string;
    private _oppponentName: string;
    private _error: string;

    //constructor 
    constructor() {
        this._status = "Waiting for opponent!";
    }

    startNewGame(symbol: string) {
        this._board = new Array(9);
        this._finished = false;
        this._winrow = new Array(3);
        this._started = true;
        this._status = "Player X is on the turn!";
        this._player = symbol;
    }

    setOneField(fieldNr: number, symbol: string): boolean {
        // if (!this._started) {
        //     return false;
        // } else if (this._finished) {
        //     return false;
        // } else if (this._board[fieldNr] != null) {
        //     return false;
        // }

        this.toogleTurn();
        this._board[fieldNr] = symbol;
        return true;
    }
    getNext(symbol: string): string {
        if (symbol === "x") {
            return "O"
        }
        return "X"
    }
    getOneField(nr:number): string{
        return this._board[nr];
    }
    setTestField(nr:number):void {
        this._board[nr] = "x";
    }
    toogleTurn(): void {
        this._turn = (this._turn == "x" ? "o" : "x");
    }
    get status():string {
        return this._status;
    }
    get board(): string[] {
        return this._board;
    }
    get turn(): string {
        return this._turn;
    }
    get finished(): boolean {
        return this._finished;
    }
    get winrow(): number[] {
        return this._winrow;
    }
    get player(): string {
        return this._player;
    }
    get started(): boolean {
        return this._started;
    }
    get oppponentName(): string {
        return this._oppponentName;
    }
    get error(): string {
        return this._error;
    }

    set status(status: string) {
        this._status = status;
    }
    set board(board: string[]) {
        this._board = board;
    }
    set turn(turn: string) {
        this._turn = turn;
    }
    set finished(finished: boolean) {
        this._finished = finished;
    }
    set winrow(winrow: number[]){
        this._winrow = winrow;
    }
    set player(player: string) {
        this._player = player;
    }
    set started(started: boolean){
        this._started = started;
    }
    set oppponentName(oppponentName: string){
        this._oppponentName = oppponentName;
    }
    set error(error: string){
        this._error = error;
    }
};
// }
