const { time } = require('node:console');
const fs = require('node:fs');

let input;

try {
  const data = fs.readFileSync('inputs/day6.txt', 'utf8');
  input = data.split(/\r?\n/);
  console.log("load complete");
} catch (err) {
  console.error(err);
}



let output1 = 1;
let output2 = 0;

//task 1

const times = input[0].split(":")[1].split(" ").filter(e => e !== "");
const distances = input[1].split(":")[1].split(" ").filter(e => e !== "");


let results = Array(times.length).fill(0);

for(let i = 0; i < times.length; i++){
    for(let j = 0; j < times[i]; j++){
        let tempdistance = j * (times[i] - j)
        if(tempdistance > distances[i]) results[i]++;
    }
}

results.forEach(e => {
    output1 *= e;
})

console.log(output1);



// task 2

const longRaceTime = parseInt(input[0].split(":")[1].replaceAll(" ", ""));
const longRaceDistance = parseInt(input[1].split(":")[1].replaceAll(" ", ""));

for(let i = 0; i < longRaceTime; i++){
    let tempdistance = i * (longRaceTime - i)
    if(tempdistance > longRaceDistance) output2++;
}

console.log(output2);