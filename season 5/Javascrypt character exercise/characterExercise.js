
const str = "Hello my friend!";

function character(string, char){

    for(i = 0; i < string.length; i++){
        if(string[i] === char){
            return true;
        }

    }
    return false;

}

console.log(character(str, "e"));
console.log(character(str, "a"));
