import * as SockJS from "sockjs-client";
import {Interfaces} from "./interfaces";
import { MsgHandler } from "./MsgHandler"

export class WebSocketConnector {
    private Stomp = require('stompjs/lib/stomp').Stomp; 

    private socket = new SockJS('/gs-guide-websocket');
    private stompClient = this.Stomp.over(this.socket);
    private game : Interfaces.IGame;

    constructor(game : Interfaces.IGame) {
        this.game = game;
        this.stompClient.connect({}, function (frame: any) {
            // this.setConnected(true);
            console.log('Connected: ' + frame);
            this.stompClient.subscribe('/topic/setField', function (field: any) {
                console.log("THIS SHOULD BE EXECUTED");
                MsgHandler.setField(JSON.parse(field.body), this.game);
            }.bind(this));
            this.stompClient.subscribe('/topic/newGame', function (player: any) {
                console.log("THIS SHOULD BE EXECUTED");
                MsgHandler.newGame(JSON.parse(player.body), this.game);
            
            }.bind(this));
            this.stompClient.subscribe('/user/topic/newGame', function (player: any) {
                console.log("THIS SHOULD BE EXECUTED");
                MsgHandler.newGame(JSON.parse(player.body), this.game);
            
            }.bind(this));
        }.bind(this))
    }

    showGreeting(message: String) {
        // $("#greetings").append(" " + message + "");
    }

    setConnected(connected: Boolean) {
        // $("#connect").prop("disabled", connected);
        // $("#disconnect").prop("disabled", !connected);
        // if (connected) {
        //     $("#conversation").show();
        // }
        // else {
        //     $("#conversation").hide();
        // }
        // $("#greetings").html("");
    }

    getStompClient() : WebSocketConnector{
        return this.stompClient;
    }

    sendField(fieldNr: number) {
        this.stompClient.send("/toServer/setField", {}, JSON.stringify({'field': fieldNr}));
    }
    sendNewGame() {
        this.stompClient.send("/toServer/newGame");
    }
}