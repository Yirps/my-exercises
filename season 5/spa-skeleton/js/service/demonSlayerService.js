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
    const character = (await response.json())[0];
    character.gallery = character.gallery.map(stripImagePath);
    character.image = stripImagePath(character.image);
    return character;
}


export default { fetchCharacters, fetchCharacter };