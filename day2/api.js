function openFile(event){
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
    var text = reader.result;
    calcScore(text);
    }
    reader.readAsText(input.files[0]);
}


let results = [];

function calcScore(_input){
    let list = [];
    let score = 0;
    list = _input.split(/\r?\n/);

    for(let i = 0; i < list.length; i++){
        if (list[i].charAt(2) == 'X'){
            if(list[i].charAt(0) == 'A') score += 4;
            if(list[i].charAt(0) == 'B') score += 1;
            if(list[i].charAt(0) == 'C') score += 7;
        }
        if (list[i].charAt(2) == 'Y'){
            if(list[i].charAt(0) == 'A') score += 8;
            if(list[i].charAt(0) == 'B') score += 5;
            if(list[i].charAt(0) == 'C') score += 2;
        }
        if (list[i].charAt(2) == 'Z'){
            if(list[i].charAt(0) == 'A') score += 3;
            if(list[i].charAt(0) == 'B') score += 9;
            if(list[i].charAt(0) == 'C') score += 6;
        }
    }


    //task 2

    let score2 = 0;

    for(let i = 0; i < list.length; i++){
        if (list[i].charAt(0) == 'A'){
            if(list[i].charAt(2) == 'X') score2 += 3;
            if(list[i].charAt(2) == 'Y') score2 += 4;
            if(list[i].charAt(2) == 'Z') score2 += 8;
        }
        if (list[i].charAt(0) == 'B'){
            if(list[i].charAt(2) == 'X') score2 += 1;
            if(list[i].charAt(2) == 'Y') score2 += 5;
            if(list[i].charAt(2) == 'Z') score2 += 9;
        }
        if (list[i].charAt(0) == 'C'){
            if(list[i].charAt(2) == 'X') score2 += 2;
            if(list[i].charAt(2) == 'Y') score2 += 6;
            if(list[i].charAt(2) == 'Z') score2 += 7;
        }
    }


    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
}