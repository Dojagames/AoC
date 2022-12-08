function openFile(event){
    var input = event.target;
    var reader = new FileReader();

    reader.onload = function() {
    var text = reader.result;
    calcScore(text);
    }
    reader.readAsText(input.files[0]);
}



function calcScore(_input){
    let list = [];
    let score = score2 = 0;
    list = _input.split(/\r?\n/);

    //task1
    for(let i = 0; i < list.length; i++){
        const x = list[i].substring(0,2);
        const y = list[i].substring(3,5);
        const z = list[i].substring(6,8);
        score += (2 * x * y) + (2 * x * z) + (2 * y * z);
        score2 += x*y*z;

        if(x*y < x*z && x*y < y*z){
            score += x*y;
            score2 += 2*x + 2*y;
        } else if(y*z < x*z){
            score += y*z;
            score2 += 2*z + 2*y;
        } else {
            score += x*z;
            score2 += 2*x + 2*z;
        }
    }

    //task 2



    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
}