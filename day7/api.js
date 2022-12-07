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
let sizes = [];
let score = 0;
let score2 = 0;

function calcScore(_input){
    list = _input.split(/\r?\n/);


    //task1
    let totalSize = recVal(0)[0];

    //task2
    let sizeToDelete = 30000000 - (70000000 - totalSize);
    score2 = Math.min(...sizes.filter(a => a > sizeToDelete));


    document.getElementById("result1").innerHTML = score;
    document.getElementById("result2").innerHTML = score2;
} 

function recVal(_index){
    let _count = 0;

    while(_index < list.length && list[_index] != "$ cd .."){
        
        if(list[_index].substring(0,4) === "$ ls"){
            let [size, i] = recVal(++_index);
            _count += size;
            _index = i;
        } else if(!isNaN(+ list[_index].split(" ")[0])){
            _count +=  + list[_index].split(" ")[0];
        }
        _index ++;
    }

    if(_count < 100000) score += _count;
    sizes.push(_count);
    return [_count, _index];
}