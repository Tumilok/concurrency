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


// The await expression causes async function execution to pause until a Promise is
// settled, and t oresume execution of the async function after fulfillment.
async function loop(number) {
  for (var i = 0; i < number; i++) {
    await task(1).then((n) => {
      console.log('task', n, 'done');
      return task(2);
    }).then((n) => {
      console.log('task', n, 'done');
      return task(3);
    }).then((n) => {
      console.log('task', n, 'done');
    });
  }
  console.log('done');
}


loop(4);
