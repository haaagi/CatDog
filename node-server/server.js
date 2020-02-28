var app = require("express")();
var server = require("http").createServer(app);
var io = require("socket.io")(server);
//setting cors
app.all("/*", function(req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  next();
});

app.get("/", function(req, res) {
  res.sendFile("Hellow Chating App Server");
});

//connection event handler

io.on("connection", function(socket) {
  console.log("Connect from Client: " + socket);
  socket.on("chat", function(data) {
    console.log(data);
    console.log("message from Client: " + data.message);

    var rtnMessage = { sender: data.sender, message: data.message };

    console.log(rtnMessage);
    // 클라이언트에게 메시지를 전송한다
    socket.broadcast.emit("chat", rtnMessage);
  });
});

server.listen(8081, function() {
  console.log("socket io server listening on port 8081");
});
