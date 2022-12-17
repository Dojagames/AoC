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
let map = [];
let score = 0;
let score2 = 0;

function calcScore(_input){
    list = _input.split(/\r?\n/);

    map = new Array(999);
    for(let i = 0; i < map.length; i++){
        map[i] = new Array(999).fill(0);
    }


    let posX = 500;
    let posY = 500;
    let TposX = 500;
    let TposY = 500;

    let lastInd = '';
    let lastNum = 0;

    //alert(list[0].substring(2,4));

    for(let i = 0; i < list.length; i++){
        const ind = list[i].charAt(0);
        const num = + list[i].substring(2,4);

        switch(ind){
            case 'U':
                for(let y = posY; y < posY + num; y++){
                    if(lastInd == 'U') map[posX][y] += 1;

                    if(lastInd == 'L' || lastInd == 'R'){
                        if(y > 0) map[posX][y] += 1;
                    }

                    if(lastInd == 'D') {
                        if(y > 1) map[posX][y] += 1;
                    } 
                }
                posY += num;
                lastInd = ind;
                lastNum = num;
                break;
            case 'D':
                for(let y = posY; y > posY - num; y--){
                    if(lastInd == 'D') map[posX][y] += 1;

                    if(lastInd == 'L' || lastInd == 'R'){
                        if(y < 0) map[posX][y] += 1;
                    }

                    if(lastInd == 'U') {
                        if(y < -1) map[posX][y] += 1;
                    } 
                }
                posY -= num;
                lastInd = ind;
                lastNum = num;
                break;
            case 'R':
                for(let x = posX; x < posX + num; x++){
                    if(lastInd == 'R') map[x][posY] += 1;

                    if(lastInd == 'U' || lastInd == 'D'){
                        if(x > 0) map[x][posY] += 1;
                    }

                    if(lastInd == 'L') {
                        if(x > 1) map[x][posY] += 1;
                    } 
                }
                posX += num;
                lastInd = ind;
                lastNum = num;
                break;
            case 'L':
                for(let x = posX; x > posX - num; x--){
                    if(lastInd == 'L') map[x][posY] += 1;

                    if(lastInd == 'U' || lastInd == 'D'){
                        if(x < 0) map[x][posY] += 1;
                    }

                    if(lastInd == 'R') {
                        if(x < -1) map[x][posY] += 1;
                    } 
                }
                posX -= num;
                lastInd = ind;
                lastNum = num;
                break;
        }

        // alert(posX + " " + posY);
    }

    for(let i = 0; i < map.length; i++){
        for(let j = 0; j < map[i].length; j++){
            if(map[i][j] > 0) score += 1;
        }
    }

    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 