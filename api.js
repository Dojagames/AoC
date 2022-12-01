function openFile(event){
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
    var text = reader.result;
    calcMax(text);
    }
    reader.readAsText(input.files[0]);
}


let results = [];

function calcMax(_input){
    let list = [];
    let buffer = 0;
    list = _input.split(/\r?\n/);

    for(let i = 0; i < list.length; i++){
        if(list[i] != "") {
            buffer += +list[i];
        } else {
            results.push(buffer);
            buffer = 0;
        }
    }

    results.sort(function(a, b) {
        return a - b;
    });

    let res =  +results[results.length -1] +results[results.length -2] + results[results.length -3];
    
    document.getElementById("result1").innerHTML = +results[results.length -1];
    document.getElementById("result2").innerHTML = res;
}