var spawn = require('child_process').spawn;
var be = spawn('java -jar comcent-area-riservata-controller-0.0.2-exec.jar', {
  shell: true
});
var fe = null;

be.stderr.on('data', function(data) {
  console.error(data.toString());
});

be.stdout.on('data', function(data) {
  console.log(data.toString());

  if (data.toString().includes('Started Main')) {
    console.log("Starting frontend...");
    fe = spawn('electron .', {
      shell: true
    });
    fe.on('exit', function(exitCode) {
      console.log("Frontend exited with code: " + exitCode);
      console.log("Terminating backend...");
      process.kill(be.pid);
      setTimeout(function () {
        console.log('\033c');
        console.log("Processo terminato. Premere CTRL + C per uscire.");
      }, 1000);
    });

  }
});

be.on('exit', function(exitCode) {
  console.log("Backend exited with code: " + exitCode);
});
