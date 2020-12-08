// Async
const async = require('async');
const walkdir = require('walkdir');
const fs = require('fs');

let globalLines = 0;

function countLinesInFile(file, cb) {
  var count = 0;
  fs.createReadStream(file).on('data', function(chunk) {
    count = chunk.toString('utf8')
      .split(/\r\n|[\n\r\u0085\u2028\u2029]/g)
      .length-1;
    globalLines += count;
  }).on('end', function() {
    console.log(file, count);
    cb();
  }).on('error', function(err) {
    console.error(err);
    cb();
  });
}

async function asyncPaths(tasks, cb) {
  var startTime = new Date();

  var counter = tasks.length;
  var callback = function() {
    counter--;
    if (counter == 0) {
      console.log("Execution time: ", new Date() - startTime);
      cb();
    }
  }
  for (var i = 0; i < tasks.length; i++) {
    tasks[i](callback);
  }
}

function getPathTask(path) {
  return (cb) => countLinesInFile(path, cb);
}

asyncPaths(
  walkdir.sync('PAM08').map(path => getPathTask(path)),
  () => console.log("Global number of lines: ", globalLines)
);
