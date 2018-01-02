var ws = null;
var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    // ws = new WebSocket('ws://localhost:8080/gs-guide-websocket');
    var socket = new SockJS('/name');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
    });
}

// function connect() {
//     ws = new WebSocket('ws://localhost:8080/name');
//     stompClient = Stomp.over(ws);
//     ws.connect({}, function (frame) {
//         ws.subscribe('/topic/greetings', function (greeting) {
//             showGreeting(JSON.parse(greeting.board).content);
//         });
//     });
//     ws.onmessage = function(data){
//         showGreeting(data.data);
//     }
//     setConnected(true);
// }

// function disconnect() {
//     if (stompClient !== null) {
//         stompClient.disconnect();
//     }
//     setConnected(false);
//     console.log("Disconnected");
// }

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

// function sendName() {
//     var obj = '{"name" : "' + $("#name").val() + '"}'
//     ws.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
//     ws.send(obj);
//
// }

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showGreeting(message) {
    $("#greetings").append(" " + message + "");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});
