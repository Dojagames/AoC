function openFile(event){
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
    var text = reader.result;
    calcScore(text);
    }
    reader.readAsText(input.files[0]);
}

let list;
let hits = [];

let yPosH = 0, xPosH = 0;
let yPosT = 0, xPosT = 0;

let score = 0;
let score2 = 0;

function calcScore(_input){
    list = _input.split(/\r?\n/);

    for(let i = 0; i < list.length; i++){
        let delta;
        let upDownBool;
        const dir = list[i].charAt(0);
        if(dir == 'D' || dir == 'L'){
            delta = (- list[i].charAt(3,4)); 
        } else {
            delta = list[i].charAt(3,4); 
        }


        if(dir == 'D' || dir == 'U'){
            upDownBool = true;
        } else {
            upDownBool = false;
        }

        

        for(let i = 0; i != delta; i += Math.sign(delta)){
            if(upDownBool){
                yPosH += Math.sign(delta);
                if(Math.abs(yPosH - yPosT) > 1) {
                    yPosT = yPosH - Math.sign(delta);
                    xPosT = xPosH;
                    hits.push([yPosT, xPosT]);
                }
            } else {
                xPosH += Math.sign(delta);
                if(Math.abs(xPosH - xPosT) > 1) {
                    xPosT = xPosH - Math.sign(delta);
                    yPosT = yPosH;
                    hits.push([yPosT, xPosT]);
                }
            }
        }
    }
    
    // let map = new Array(999);
    // for(let i = 0; i < map.length; i++){
    //     map[i] = new Array(999).fill(0);
    // }

    // for(let i = 0; i < hits.length; i++){
    //     map[500 + hits[i][0]][500 + hits[i][1]] += 1;
    //     alert([500 + hits[i][0]]+ " " + [500 + hits[i][1]]);
    // }

    // for(let i = 0; i < map.length; i++){
    //     for(let j = 0; j < map[i].length; j++){
    //         if(map[i][j] > 0) score += 1;
    //     }
    // }

    let uniqueChars = [...new Set(hits)];

    score = uniqueChars.length + 1;
    

    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 