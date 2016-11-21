# spring boot websockets and async scheduler

### Usage

```
git clone https://github.com/shamsmali/spring-websockets.git
cd spring-websockets
mvn clean install
```

now import it to intellij as maven project and then run App.java class


### Starting scheduler
curl -X PUT  http://localhost:8080/api/poller/start/1     --- 1 seconds -- so it will spawn a async task everysecond

curl -X GET  http://localhost:8080/api/poller/stop        --- to stop the scheduler

curl -X GET  http://localhost:8080/api/poller/listeners   --- gives you id of all the websockets sessions

curl -X DELETE  http://localhost:8080/api/poller/listeners/1  --- temrinates web socket session with id 1

### Websocket client

```
npm install --save ws

var WebSocket = require('ws');
var ws = new WebSocket('ws://127.0.0.1:8080/api/ws');
ws.on('message', function(data, flags) {
   console.log(data);
});
```

save this to a file and run node <file_name>.js and you should see below,
```
Hello ---  0   ---- websocket session id
Hello ---  0
Hello ---  0
```
