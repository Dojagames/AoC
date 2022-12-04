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
    let score2 = 0;
    let int1, int2, int3, int4;
    
    list = _input.split(/\r?\n/);

    for(let i = 0; i < list.length; i++){
        int1 = parseInt(list[i].substring(0,2));
        int2 = parseInt(list[i].substring(3,5));
        int3 = parseInt(list[i].substring(6,8));
        int4 = parseInt(list[i].substring(9,11));

        //task 1
        if((int1 >= int3 && int2 <= int4) || (int1 <= int3 && int2 >= int4)) score++;

        //task 2
        if((int2 >= int3 && int1 <= int3) || (int4 >= int1 && int3 <= int1)) score2++;
    }


    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 