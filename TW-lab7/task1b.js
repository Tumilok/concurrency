function printAsync(s, cb) {
  var delay = Math.floor((Math.random() * 1000) + 500);
  setTimeout(function () {
    console.log(s);
    if (cb) cb();
  }, delay);
}

function task(n) {
  return new Promise((resolve, reject) => {
    printAsync(n, function () {
      resolve(n);
    });
  });
}

/*
** Zadanie:
** Napisz funkcje loop(m), ktora powoduje wykonanie powyzszej
** sekwencji zadan m razy.
**
*/


// This is the callback where all the task results are passed and is
// executed once all the task execution has completed.
// It will run one function at a time and pass the result of the
// previous function to the next one.

var async = require('async')

function print(cb) {
  task(1).then((n) => {
    console.log('task', n, 'done');
    return task(2);
  }).then((n) => {
    console.log('task', n, 'done');
    return task(3);
  }).then((n) => {
    console.log('task', n, 'done');
    cb();
  });
}


async.waterfall([print, print, print, print], () => console.log('done'))
