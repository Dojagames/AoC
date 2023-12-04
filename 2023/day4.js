const fs = require('node:fs');

let input;

try {
  const data = fs.readFileSync('inputs/day4.txt', 'utf8');
  input = data.split(/\r?\n/);
  console.log("load complete");
} catch (err) {
  console.error(err);
}

let output1 = 0;
let output2 = 0;

//task 1


for(let i = 0; i < input.length; i++){
    let points = 0;
    const winningTable = input[i].split(":")[1].split("|")[0].split(" ").filter(e => e !== "");
    const yourTable = input[i].split(":")[1].split("|")[1].split(" ").filter(e => e !== "");
    yourTable.forEach((e) => {
        if(winningTable.includes(e)){
            points ? points *= 2 : points = 1;
        }
    })

    output1 += points;
}

console.log(output1);




// task 2
let instances = [];

for(let i = 0; i < input.length; i++){
    instances.push(1);
}

for(let i = 0; i < input.length; i++){
    let hits = 0;
    output2 += instances[i];
    input[i].split(":")[1].split("|")[1].split(" ").filter(e => e !== "").forEach((e) => {
        if(input[i].split(":")[1].split("|")[0].split(" ").filter(e => e !== "").includes(e)){
            hits++;
            instances[i+hits] += instances[i];
        }
    });
};


console.log(output2);