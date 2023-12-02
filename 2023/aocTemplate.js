const fs = require('node:fs');

let input;

try {
  const data = fs.readFileSync('inputs/day1.txt', 'utf8');
  input = data.split(/\r?\n/);
  console.log("load complete");
} catch (err) {
  console.error(err);
}

let output1;
let output2;

//task 1









// task 2