function openFile(event){
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
    var text = reader.result;
    calcMax(text);
    }
    reader.readAsText(input.files[0]);
}



function calcMax(_input){
    let score = score2 = 0;
    let task2 = true;

    for(let i = 0; i < _input.length; i++){
        if(_input.charAt(i) == '(') {
            score++;
        } else score--;

        if(task2 && score == -1) {
            score2 = i + 1;
            task2 = false;
        }
    }
    
    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
}