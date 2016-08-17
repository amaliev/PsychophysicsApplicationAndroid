# PsychophysicsApplicationAndroid

An Android application to be used for a psychophysics study at WINLAB. More details about the project and study can be found at http://sites.google.com/site/psychophysicsapp.

# Running the application

To edit and build the application, download the main file and move it to the /src/ directory in an Android Studio project.

# Running the server

Before running the server, install the packages by running the following command: 
```javascript
npm install
```
Commands: 
```javascript
node faye_server.js
```
Running this line will start the server and allow clients to connect.
```javascript
node faye_publish.js start
```
Running this line will publish "start" and bring the connected clients into the experiment.
```javascript
node faye_publish.js switch
```
Running this line will publish "switch" and prompt the users to rate the current video segment.
```javascript
node faye_publish.js count
```
Running this line will publish "count" and display how many clients are currently connected.
```javascript
node faye_dataorg.js
```
Running this line at the end of the experiment will organize and display all of the clients' input.

# References

[Faye](https://github.com/faye/faye) - server that PsychophysicsApplicationAndroid connects to
[FayeAndroid](https://github.com/elirex/android-faye-client) - client that PsychophysicsApplicationAndroid implements
[FayeSwift](https://github.com/hamin/FayeSwift) - implementation of Faye server utilized in the sample files

# License

This project is licensed under the MIT License.
