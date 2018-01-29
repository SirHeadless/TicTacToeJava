export namespace Interfaces {

    interface IMsgInfo {
        type: string,
        message: string,
    }

    interface IGame {
        board: string[],
        turn: string,
        finished: boolean,
        winrow: number[],
        player: string,
        started: boolean,
        status: string,
        error: string,


        setOneField(fieldNr: number, symbol: string): boolean,
        toogleTurn(): void,
        getNext(symbol:string): string,
        startNewGame(symbol: string):void,
        loadGame(board: string[],
            player: string,
            opponentName: string,
            turn: string,): boolean,
    }

    interface IFieldMessage {
        field: number,
        player: string,
    }

    interface IWonMsg {
        winnerRow: number[],
        winner: string,
    }

    interface INewGame {
        player: string,
        oppponentName: string,
    }

    interface INewOpponent {
        symbol: string,
    }

    interface IGameOver{
        winnerRow: number[],
        winner: string,
        full: boolean,
    }

    interface IError{
        error: string,
    }

    interface ILoadGame{
        loadGame: Boolean,
        board: string[],
        player: string,
        opponentName: string,
        turn: string,
    }
}