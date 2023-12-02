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
let score = 392;
let score2 = 0;

function calcScore(_input){
    list = _input.split(/\r?\n/);
    
    
    for(let i = 0; i < list.length; i++){
        let tmpArr = [];
        for(let j = 0; j < list[i].length; j++){
            tmpArr.push(+ list[i].charAt(j));
        }
        map.push(tmpArr);
    }

    // map[y][x]
    // map[i][j]

    //task1
    for (let y = 1; y < map.length - 1; y++) {
        for (let x = 1; x < map[y].length - 1; x++) {

            let isVisible = true;
            for (let i = 0; i < x; i++) {
                if (map[y][i] >= map[y][x]) isVisible = false;
            }
            if (isVisible) {
                score++;
                continue;
            }

            isVisible = true;
            for (let i = x + 1; i < map[y].length; i++) {
                if (map[y][i] >= map[y][x]) isVisible = false;
            }
            if (isVisible) {
                score++;
                continue;
            }

            isVisible = true;
            for (let i = 0; i < y; i++) {
                if (map[i][x] >= map[y][x]) isVisible = false;
            }
            if (isVisible) {
                score++;
                continue;
            }

            isVisible = true;
            for (let i = y + 1; i < map.length; i++) {
                if (map[i][x] >= map[y][x]) isVisible = false;
            }
            if (isVisible) {
                score++;
                continue;
            }
        }
    }

    //task2
    for(let y = 1; y < map.length - 1; y++){
        for(let x = 1; x < map[y].length - 1; x++){

            let temp = [0, 0, 0, 0];
            for (let i = x - 1; i >= 0; i--) {
                temp[0]++;
                if (map[y][i] >= map[y][x]) {
                    break;
                }
            }
            for (let i = x + 1; i < map.length; i++) {
                temp[1]++;;
                if (map[y][i] >= map[y][x]) {
                    break;
                }
            }
            for (let i = y - 1; i >= 0; i--) {
                temp[2]++;
                if (map[i][x] >= map[y][x]) {
                    break;
                }
            }
            for (let i = y + 1; i < map.length; i++) {
                temp[3]++;
                if (map[i][x] >= map[y][x]) {
                    break;
                }
            }
            score2 = Math.max(score2, temp.reduce((a, b) => a * b, 1))

        }
    }


    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 