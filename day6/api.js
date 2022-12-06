function openFile(event){
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
    var text = reader.result;
    calcScore(text);
    }
    reader.readAsText(input.files[0]);
}


let findDuplicates = arr => arr.filter((item, index) => arr.indexOf(item) != index)

function calcScore(_input){
    let list = [];
    let score = 0;
    let score2 = 0;
    

    //task1
    for(let i = 0; i < _input.length; i++){
        for(let j = 0; j < 4; j++){
            list[j] = _input.charAt(i + j);
        }

        if(findDuplicates(list).length == 0){
            score = i + 4;
            break
        }
    }

    //task2
    for(let i = 0; i < _input.length; i++){
        for(let j = 0; j < 14; j++){
            list[j] = _input.charAt(i + j);
        }
        
        
        if(findDuplicates(list).length == 0){
            score2 = i + 14;
            break
        }
    }


    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 