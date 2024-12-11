const wordArr = ['Hello','Kachow', 'Joy', 'Javabank'];

const numArr = [5, 9 , 5, 6, 10, 39836, 98325, 83, 649.43, -14];

const numArr2 = [5, 9 , 5, 6, 10]; //35

function filterWord(array, condition) {
    const filteredWords = [];
    for (let i = 0; i < array.length; i++) {
        if (array[i].length < condition) {
            filteredWords.push(array[i]);
        }
    }
    return filteredWords;
}


function filterNum(array, condition) {
    const filteredNum = [];
    for (let i = 0; i < array.length; i++) {
        if (array[i] < condition) {
            filteredNum.push(array[i]);
        }
    }
    return filteredNum;  
}

function wordMap(array, condition){

    const mappedWord = [];

    for(i = 0; i < array.length; i++){
        
            result = array[i] + condition;
            mappedWord.push(result);
            
    }
    return mappedWord;
}

function numMap(array, condition){

    const mappedNum = [];

    for(i = 0; i < array.length; i++){
        
            result = array[i] + condition;
            mappedNum.push(result);
            
    }
    return mappedNum;
}

function reduce(array){

    let accumulator = 0;

    for(i = 0; i < array.length; i++){

        accumulator += array[i];
    }

    return accumulator;
}

console.log(filterNum(numArr, 20));

console.log(filterWord(wordArr, 5));

console.log(wordMap(wordArr, "AAAA"));

console.log(numMap(numArr, 5));

console.log(reduce(numArr2));