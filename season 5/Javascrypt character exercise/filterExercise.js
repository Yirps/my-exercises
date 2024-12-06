let characters = [
    { name: "John", email: "john_the_one@gmail.com", age: 18 },
    { name: "Diane", email: "princess.diane@gmail.com", age: 43 },
    { name: "Snoop", email: "hip-hop@gmail.com", age: 4 },
    { name: "Ice T", email: "OG_ice@gmail.com", age: 14 },
    { name: "Vanilla Ice", email: "wannabeIce@gmail.com", age: 216 },
    { name: "Eminem", email: "theOne@gmail.com", age: 17 },
];

const criteria1 = {
    age: 18,
    email: "gmail.com",
};

const criteria2 = {
    age: 14,
    email: "gmail.com",
};

const criteria3 = {
    age: 13,
    email: "gmail.com",
};

function filter(list, criteria){

    for(i = 0; i < list.length; i++){
        if(list[i].email.includes(criteria.email) && list[i].age === criteria.age ){
            return list[i].name;
        }
    }
    
    return false;
}

console.log(characters[2].email);

console.log(filter(characters, criteria1));
console.log(filter(characters, criteria2));
console.log(filter(characters, criteria3));


let arr = [ 1, 'fine', true ];
arr[3] = { name: 'Neo', chosenOne: true };

console.log(arr[0]); // 1
console.log(arr[1]); // 'fine'
console.log(arr[2]); // true
console.log(arr[3]); // { name: 'Neo', chosenOne: true }