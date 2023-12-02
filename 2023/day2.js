const fs = require('node:fs');

let input;

try {
  const data = fs.readFileSync('inputs/day2.txt', 'utf8');
  input = data.split(/\r?\n/);
  console.log("load complete");
} catch (err) {
  console.error(err);
}

let output1 = 0;
let output2 = 0;

//task 1


for(let i = 0; i < input.length; i++){
    const id = input[i].split(" ")[1].slice(0, -1);

    let run = input[i].split(":")[1].split(";");

    
    let runPosible = [];
    for(let j = 0; j < run.length; j++){
        let runDraws = {
            red: 0,
            blue: 0,
            green: 0,
        }

        run[j].split(",").forEach(e => {
            if(e.includes("red")){
                runDraws.red += parseInt(e.split(" ")[1]);
            } else if(e.includes("blue")){
                runDraws.blue += parseInt(e.split(" ")[1]);
            } else if(e.includes("green")){
                runDraws.green += parseInt(e.split(" ")[1]);
            }
        });

       if(runDraws.red <= 12 && runDraws.green <= 13 && runDraws.blue <= 14) {
        runPosible.push(true);
       } else {
        runPosible.push(false);
        break;
       }
        
    }
    
    if(!runPosible.includes(false)){
        output1 += parseInt(id);
    }
}
console.log(output1);

let runcubes = [];
// task 2
for(let i = 0; i < input.length; i++){
    const id = input[i].split(" ")[1].slice(0, -1);

    let run = input[i].split(":")[1].split(";");

    
    runcubes.push({
        redMin: 0,
        blueMin: 0,
        greenMin: 0,
    });

    for(let j = 0; j < run.length; j++){
        

        run[j].split(",").forEach(e => {
            if(e.includes("red")){
                if(parseInt(e.split(" ")[1]) > runcubes[i].redMin) {runcubes[i].redMin = parseInt(e.split(" ")[1]);}
            } else if(e.includes("blue")){
                if(parseInt(e.split(" ")[1]) > runcubes[i].blueMin) { runcubes[i].blueMin = parseInt(e.split(" ")[1]);}
            } else if(e.includes("green")){
                if(parseInt(e.split(" ")[1]) > runcubes[i].greenMin)  {runcubes[i].greenMin = parseInt(e.split(" ")[1]);}
            }
        });
    }
    output2 += parseInt(runcubes[i].redMin * runcubes[i].blueMin * runcubes[i].greenMin);
}

console.log(output2);
