const users = require("./users.js");

console.log(users);

/*
const oddId = users.filter((users) => users.id % 2 === 1);

console.log(oddId[1]);

//const objectArr = oddId.map(({ id, ...userDetails }) => ({ [id]: userDetails }));
const objectArr = oddId.map(oddId => ({ [oddId.id]: { oddId } }));

console.log(objectArr[0]);



function idToIndex(array){

    const finalArr = [];  // Initialize an empty array to hold the final result

    for (let i = 0; i < array.length; i++) {
        const currentObject = array[i];  // Current object in the array
        finalArr[currentObject.id] = currentObject;  // Use the id as the index
    }

    return finalArr;
}

console.log(idToIndex(objectArr));
*/

// Step 1: Filter users with odd ids
const oddId = users.filter(user => user.id % 2 === 1);

console.log(oddId);  // Log the filtered oddId users

// Step 2: Map the filtered users into an object with the `id` as the key and the user object as the value
const objectArr = oddId.map((user, index) => ({ [user.id]: { ...user, index } }));

console.log(objectArr);  // Log the mapped object array

// Step 3: The final transformation using reduce to turn the array into an object
const finalObject = objectArr.reduce((acc, curr) => {
    const id = Object.keys(curr)[0]; // Get the `id` as the key
    acc[id] = curr[id];  // Assign the user object to the `id` key
    return acc;
}, {});

console.log(finalObject);  // Log the final transformed object
