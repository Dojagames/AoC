const fs = require('node:fs');
const antiSymbols = ["0","1","2","3","4","5","6",'7',"8","9", "."];
const chars = ['*', '=', '+', '/', '&', '#', '%', '-', '$', "@"];
const numbers = ["0","1","2","3","4","5","6",'7',"8","9"];

let input;

try {
  const data = fs.readFileSync('inputs/day3.txt', 'utf8');
  input = data.split(/\r?\n/);
  console.log("load complete");
} catch (err) {
  console.error(err);
}

let output1 = 0;
let output2;

//task 1

let buffer = [];

for(let i = 0; i < input.length; i++){
  let tempNumber = "";
  let tempHit = false;

  for(let j = 0; j < input[i].length; j++){
    if(numbers.includes(input[i][j])){
      tempNumber += input[i][j];
      if(!tempHit) tempHit = CheckValidNumber(i, j);
    } else {
      if(tempHit && tempNumber){
        console.log(tempNumber);
        //buffer.push(tempNumber);
        output1 += parseInt(tempNumber);
      }
      tempNumber = "";
      tempHit = false;
    }
  }
}





function CheckValidNumber(_x, _y){
  let valid = false;
  for (let i = -1; i <= 1; i++){
    for(let j = -1; j <= 1; j++){
      if(CheckIfInBound(_x + i, _y + j)){
        if(chars.includes(input[_x + i][_y + j])){
          valid = true;
        }
      }
    }
  }
  return valid;
}

function CheckIfInBound(_x, _y){
  if(_x < 0 || _x >= input[0].length || _y < 0 || _y >= input.length){
    return false
  }
  return true
}

console.log(output1);

// task 2