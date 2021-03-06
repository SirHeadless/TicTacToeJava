import * as React from "react";
import * as ReactDOM from "react-dom";
import * as SockJS from "sockjs-client";

import "./components/Hello.tsx";
import { Game } from "./components/Game";
import { MsgMaker } from "./components/MsgMaker"
import { MsgHandler } from "./components/MsgHandler"
import './components/test.css';
import {WebSocketConnector} from "./components/WebSocketConnector";
import {Interfaces} from "./components/interfaces";
import IGame = Interfaces.IGame;

const TITLE = "Tic-Tac-Toe";

// BEIDE OBJECTE SOLLTEN LIEBER UEBERGEBEN WERDEN ALS SIE GLOBAL ZU MACHEN !?
// var wsClient = new WebSocket('ws://' + window.location.host + '/name');

class Field extends React.Component<any, any> {
    keyNr = (this.props.coordi * 3 + this.props.coordj)
    keyField = "field" + this.keyNr;
    game : IGame;

    handleClick() {
        console.log("Board: " + this.props.game.board)
        this.props.wsClient.send("/app/setField", {}, JSON.stringify({'field': this.keyNr, 'player': this.props.game.player}));
        this.setState({game : this.props.game})
    }
    render() {
        var fieldSymbol = this.props.game.getOneField(this.keyNr);
        return <td key={this.keyField.toString()} onClick={this.handleClick.bind(this)} className="field"><p>{fieldSymbol}</p> </td>
    }
};

class ErrorMessage extends React.Component<any, any> {
    render() {
        return <b key="ErrorMessage" className="errorMessage"> {this.props.error} </b>
    }
}

class Title extends React.Component<any, any> {
    render() {
        return <h2> {TITLE} </h2>
    }
}


class FindOpponentButton extends React.Component<any, any> {
    handleClick() {
        this.props.wsClient.send("/app/findOpponent");
    }
    render() {
        return (
            <div>
                <button onClick={this.handleClick.bind(this)}>
                 Find Opponent Game
                </button>
            </div>
        )
    }
}

class NewGameButton extends React.Component<any, any> {
    handleClick() {
        this.props.wsClient.send("/app/newGame");
        this.props.game.newGame()
    }
    render() {
        var button = []
        if (this.props.game.finished) {
            button.push(<button onClick={this.handleClick.bind(this)}>
                New Game
        </button>);
        }
        return (
            <div>
                {button}
            </div>
        )
    }
}

class TestLoadGameButton extends React.Component<any, any> {
    handleClick() {
        this.props.wsClient.send("/app/loadGame");
    }

    render() {
        return (
            <div>
                <button onClick={this.handleClick.bind(this)}>
                Load Game
                </button>
            </div>
        )
    }
}

class StatusMsg extends React.Component<any, any> {


    render() {
        var turn = []

        if (this.props.player) {
            turn.push(<div>
                You are player {this.props.player}
            </div>);
        }

        return (
            <div>
                {turn}
                <div>
                    {this.props.status}
                </div>
            </div>
        )
    }
}

class Board extends React.Component<any, any> {
    render() {
        var row = [];
        var cells = [];
        for (var i = 0; i < 3; i++) {
            for (var j = 0; j < 3; j++) {


                cells.push(<Field coordi={i} coordj={j} game={this.props.game} wsClient={this.props.wsClient}/>);
            }
            var keyRow = "row" + i;
            row.push(<tr key={keyRow}>{cells}</tr>);
            cells = [];
        }
        return (
            <table key="TicTacToe" className="TicTacToe">
                <tbody key="board" className="board">
                    {row}
                </tbody>
            </table>
        )
    }
};

class ContainerView extends React.Component {
    // game : IGame = null;
    // wsClient : WebSocketConnector = null;
    game = new Game();
    private Stomp = require('stompjs/lib/stomp').Stomp; 
    
    private socket = new SockJS('/gs-guide-websocket');
    private wsClient = this.Stomp.over(this.socket);

    waitForSocketConnection(socket: any, callback: any) {

        setTimeout(
            function () {
                if (socket.readyState === 1) {
                    console.log("Connection is made")
                    if (callback != null) {
                        callback();
                    }
                    return;

                } else {
                    console.log("wait for connection...")
                    this.waitForSocketConnection(socket, callback);
                }

            }, 5); // wait 5 milisecond for the connection...
    }

    componentDidMount() {

        this.wsClient.connect({}, function (frame: any) {
            // this.setConnected(true);
            console.log('Connected: ' + frame);
            this.wsClient.subscribe('/user/topic/setField', function (field: any) {
                console.log("THIS SHOULD BE EXECUTED");
                MsgHandler.setField(JSON.parse(field.body), this.game);
                this.setState(this.game)
            }.bind(this));
            this.wsClient.subscribe('/user/topic/gameOver', function (gameOver: any) {
                console.log("Game is Over");
                MsgHandler.gameOver(JSON.parse(gameOver.body), this.game);
                this.setState(this.game)
            }.bind(this));
            this.wsClient.subscribe('/user/topic/newGame', function (player: any) {
                console.log("THIS SHOULD BE EXECUTED NEW GAME");
                MsgHandler.newGame(JSON.parse(player.body), this.game);
                this.setState(this.game)
            }.bind(this));
            this.wsClient.subscribe('/topic/join', function (field: any) {
                console.log("THIS SHOULD BE EXECUTED");
                MsgHandler.setField(JSON.parse(field.body), this.game);
                this.setState(this.game)
            }.bind(this));
            this.wsClient.subscribe('/topic/opponentFound', function (field: any) {
                console.log("THIS SHOULD BE EXECUTED");
                MsgHandler.setField(JSON.parse(field.body), this.game);
                this.setState(this.game)
            }.bind(this));
            this.wsClient.subscribe('/user/topic/error', function (error: any) {
                console.log(error.body);
                MsgHandler.setErrorMessage(JSON.parse(error.body), this.game);
                this.setState(this.game);
            }.bind(this));
            this.wsClient.subscribe('/user/topic/loadGame', function (gameToLoad: any) {
                console.log(gameToLoad.body);
                MsgHandler.setGame(JSON.parse(gameToLoad.body), this.game);
                this.setState(this.game);
            }.bind(this));
            this.wsClient.subscribe('/user/topic/connect', function (gameToLoad: any) {
                console.log("Received test call ME");
            }.bind(this));
            this.wsClient.subscribe('/topic/connect', function (gameToLoad: any) {
                console.log("Received test call ALL");
            }.bind(this));
            this.wsClient.subscribe('/app/connect', function (gameToLoad: any) {
                console.log("Received test call ALL");
            }.bind(this));
            this.wsClient.subscribe('/user/app/connect', function (gameToLoad: any) {
                console.log("Received test call ME");
            }.bind(this));
        }.bind(this))


        // this.waitForSocketConnection(wsClient, function () {
        //     console.log("Send newGame");
        //     wsClient.sendNewGame();
        //     console.log(MsgMaker.newGame())
        // });


        // wsClient.onmessage = event => {
        //     console.log("Got message" + event.data)
        //     if (MsgHandler.handle(event.data, this.game)) {
        //         this.setState(this.game)
        //     }
        // }
    }

    render() {
        return (
            <div>
                <Title />
                <ErrorMessage error={this.game.error}/>
                <FindOpponentButton game={this.game} wsClient={this.wsClient}/>
                <StatusMsg status={this.game.status} player={this.game.player} finished={this.game.finished} />
                <NewGameButton game={this.game} wsClient={this.wsClient}/>
                <Board game={this.game} wsClient = {this.wsClient}/>
            </div>
        )
    }
};

ReactDOM.render(

    <ContainerView />,    document.getElementById("example")
);


