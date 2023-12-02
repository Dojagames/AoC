const fs = require('node:fs');

let input;

try {
  const data = fs.readFileSync('inputs/day1.txt', 'utf8');
  input = data.split(/\r?\n/);
  console.log("load complete");
} catch (err) {
  console.error(err);
}


const numbers = ["0","1","2","3","4","5","6",'7',"8","9"];

//task 1;

let sum = 0;
let sum2 = 0;

for(let i = 0; i < input.length; i++){
  let nb = "";

  for(let j = 0; j < input[i].length; j++){
    if(numbers.includes(input[i].charAt(j))){
      nb += input[i].charAt(j);
      break;
    }
  }

  for(let j = input[i].length - 1; j >= 0 ; j--){
    if(numbers.includes(input[i].charAt(j))){
      nb += input[i].charAt(j);
      break;
    }
  }

  sum += parseInt(nb);
}
console.log(sum);


//task 2

for(let i = 0; i < input.length; i++){
  let nb = "";
  

  input[i] = input[i].replaceAll("one", "o1e");
  input[i] = input[i].replaceAll("two", "t2o");
  input[i] = input[i].replaceAll("three", "th3ee");
  input[i] = input[i].replaceAll("four", "f4ur");
  input[i] = input[i].replaceAll("five", "f5ve");
  input[i] = input[i].replaceAll("six", "s6x");
  input[i] = input[i].replaceAll("seven", "se7en");
  input[i] = input[i].replaceAll("eight", "ei8ht");
  input[i] = input[i].replaceAll("nine", "n9ne");


  for(let j = 0; j < input[i].length; j++){
    if(numbers.includes(input[i].charAt(j))){
      nb += input[i].charAt(j);
      break;
    }
  }

  for(let j = input[i].length; j >= 0 ; j--){
    if(numbers.includes(input[i].charAt(j))){
      nb += input[i].charAt(j);
      break;
    }
  }


  sum2 += parseInt(nb);
}

console.log(sum2);