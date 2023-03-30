var stompClient = null;
var notificationCount = 0;

$(document).ready(function() {
    console.log("Index page is ready");

    $("#send").click(function() {
        sendMessage();
    });

    $("#send-private").click(function() {
        sendPrivateMessage();
    });

    $("#notifications").click(function() {
        resetNotificationCount();
    });

    $("#connect").click(function() {
        connect();
    });
});

function connect() {
    var ts = document.getElementById("ts").value;
    var key = document.getElementById("key").value;
    var deviceId = document.getElementById("deviceId").value;
    var authorization = document.getElementById("authorization").value;


    var socket = new SockJS('/websocket');
    stompClient = Stomp.over(socket);
    var headers = {
        TS: ts,
        PKey: key,
        deviceId: deviceId,
        Authorization: authorization
    };
    stompClient.connect(headers, function(frame) {
        console.log('Connected: ' + frame);
        updateNotificationDisplay();
        stompClient.subscribe('/receive/message', function(message) {
            showMessage(JSON.parse(message.body).content);
        });

        stompClient.subscribe('/receive/global-notification', function(message) {
            notificationCount = notificationCount + 1;
            updateNotificationDisplay();
        });

       stompClient.subscribe('/receive/private-message/' + deviceId, function(message) {
        showMessage(JSON.parse(message.body).content);
       });

       stompClient.subscribe('/receive/private-notification/' + deviceId, function(message) {
        notificationCount = notificationCount + 1;
        updateNotificationDisplay();
    });

    });

}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
}

function sendMessage() {
    console.log("sending message");
    stompClient.send("/ws/message", {}, JSON.stringify({
        'messageContent': $("#message").val()
    }));
}

function sendPrivateMessage() {
    console.log("sending private message");
    stompClient.send("/ws/private-message" , {}, JSON.stringify({
        'messageContent': $("#private-message").val(),
        'receiver': $("#receiver-id").val()
    }));
}

function updateNotificationDisplay() {
    if (notificationCount == 0) {
        $('#notifications').hide();
    } else {
        $('#notifications').show();
        $('#notifications').text(notificationCount);
    }
}

function resetNotificationCount() {
    notificationCount = 0;
    updateNotificationDisplay();
}