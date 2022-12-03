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
    let finalStr = "";
    let finalStr2 = "";
    let str1, str2, str3;
    list = _input.split(/\r?\n/);

    for(let i = 0; i < list.length; i++){
       str1 = list[i].substring(0, (list[i].length) / 2);
       str2 = list[i].substring((list[i].length) / 2, list[i].length);


       for(let j = 0; j < str2.length; j++){
           if(str1.indexOf(str2.charAt(j)) > -1) {
               finalStr += str2.charAt(j);
               break;
           }
       }
    }

    for(let i = 0; i < finalStr.length; i++){
        if (finalStr.charAt(i) == finalStr.charAt(i).toUpperCase()) {
            score += finalStr.charCodeAt(i) - 38;
        }
        if (finalStr.charAt(i) == finalStr.charAt(i).toLowerCase()){
            score += finalStr.charCodeAt(i) - 96;
        }
    }



    //task 2

    let score2 = 0;
    let skip = false;
    for(let i = 0; i < list.length -2; i+=3){
        skip = false;

        str1 = list[i];
        str2 = list[i+1];
        str3 = list[i+2];

        //alert(str1 + "\n" + str2 + "\n" + str3 + "\n" )

        for(let j = 0; j < str2.length; j++){
            if(str1.indexOf(str2.charAt(j)) > -1){
                if(skip == true) break;
                
                for(let k = 0; k < str3.length; k++){
                    if(str2.charAt(j) == str3.charAt(k)) {
                        finalStr2 += str2.charAt(j);
                        skip = true;
                        break;
                    }
                }
            }
        }
    }

    for(let i = 0; i < finalStr2.length; i++){
        if (finalStr2.charAt(i) == finalStr2.charAt(i).toUpperCase()) {
            score2 += finalStr2.charCodeAt(i) - 38;
        }
        if (finalStr2.charAt(i) == finalStr2.charAt(i).toLowerCase()){
            score2 += finalStr2.charCodeAt(i) - 96;
        }
    }


    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 