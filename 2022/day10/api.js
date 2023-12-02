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
let tempScore = 1;

let tempList = [];

let score = 0;
let score2 = 0;

function calcScore(_input){
    list = _input.split(/\r?\n/);
    temp = 0;

    for(let i = 0; i < list.length; i++){
        if(list[i].substring(0,4) == "noop"){
            tempList.push(tempScore);
            temp++;
        } else{
            tempList.push(tempScore);
            temp ++;
            tempList.push(tempScore);
            temp ++;
            tempScore += parseInt(list[i].substring(5)); 
        }

    }

    for (let i = 20; i <= 220; i += 40) {
        score += tempList[i] * i;
    }
    

   
    

    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 