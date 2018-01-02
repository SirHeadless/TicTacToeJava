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


        setOneField(fieldNr: number, symbol: string): boolean,
        toogleTurn(): void,
        getNext(symbol:string): string,
        startNewGame(symbol: string):void,
    }

    interface IFieldMessage {
        field: number,
        player: string,
    }

    interface IWonMsg {
        winRow: number[],
        winner: string,
    }

    interface INewGame {
        symbol: string,
    }

    interface INewOpponent {
        symbol: string,
    }
}