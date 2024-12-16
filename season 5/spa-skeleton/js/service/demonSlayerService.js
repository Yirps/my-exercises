function stripImagePath(image) {
    return image.replace(/.revision.*$/, "");
}

async function fetchCharacters() {
    const api = "https://demon-slayer-api.onrender.com/v1/";
    const response = await fetch(api);
    const characters = await response.json();

    return characters.map(character => ({
        name: character.name,
        image: stripImagePath(character.image),
        url: character.url.replace(api, '')
    }));
}

async function fetchCharacter(urlName) {
    const api = `https://demon-slayer-api.onrender.com/v1/${urlName}`;
    const response = await fetch(api);
    const character = await response.json();
    character.gallery = character.gallery.map(stripImagePath);
    character.image = stripImagePath(character.image);
    
    return {
        name: character.name,
        image: character.image,
        gallery: character.gallery,
        race: character.race,
        gender: character.gender,
        age: character.age,
        height: character.height,
        weight: character.weight,
        birthday: character.birthday
    };
}


export default { fetchCharacters, fetchCharacter };