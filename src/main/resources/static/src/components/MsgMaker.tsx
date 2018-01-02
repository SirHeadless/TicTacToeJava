import * as React from "react";

export namespace MsgMaker {
    export function newGame() { return JSON.stringify({
        Message: "test",
        MsgType: "newGame"
    }
    )}

    export function setField(field: number) {return JSON.stringify({
        Message: {field: field},
        MsgType: "field"
    }
    )}

} 