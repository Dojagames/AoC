function openFile(event){
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
    var text = reader.result;
    calcScore(text);
    }
    reader.readAsText(input.files[0]);
}

let def = [
    ['D','B','J','V'],
    ['P','V','B','W','R','D','F'],
    ['R','G','F','L','D','C','W','Q'],

    ['W','J','P','M','L','N','D','B'],
    ['H','N','B','P','C','S','Q'],
    ['R','D','B','S','N','G'],

    ['Z','B','P','M','Q','F','S','H'],
    ['W','L','F'],
    ['S','V','F','M','R']
];


var def2 = def.map((arr) => { return arr.slice(); });


function calcScore(_input){
    let list = [];
    let int1, int2, int3;
    
    list = _input.split(/\r?\n/);

    //task1
    for(let i = 0; i < list.length; i++){
        int1 = parseInt(list[i].substring(5,7));
        int2 = parseInt(list[i].substring(13,15)) - 1;
        int3 = parseInt(list[i].substring(19,21)) - 1;

        
        let bufferArray = [];
        bufferArray.length = int1;

        for(let i = 0; i < int1; i++){
            def[int3].push(def[int2][def[int2].length - 1]);
            def[int2].pop();
        }
    }


    //task2
    for(let i = 0; i < list.length; i++){
        int1 = parseInt(list[i].substring(5,7));
        int2 = parseInt(list[i].substring(13,15)) - 1;
        int3 = parseInt(list[i].substring(19,21)) - 1;

        
        let bufferArray = [];
        bufferArray.length = int1;

        for(let i = 0; i < int1; i++){
            bufferArray[i] = def2[int2][def2[int2].length - 1];
            def2[int2].pop();
        }

        for(let i = 0; i < int1; i++){
            def2[int3].push(bufferArray[bufferArray.length - i - 1]);
        }
    }
    
    let finalString = "";
    let finalString2 = "";

    for(let i = 0; i < def.length; i++){
        finalString += def[i][def[i].length - 1];
        finalString2 += def2[i][def2[i].length - 1];
    }


    document.getElementById("result1").innerHTML = finalString;
    document.getElementById("result2").innerHTML = finalString2;
} 