function renderCharacter(character, characters, fetchCharacter) {
    const container = document.querySelector('#container');
    container.innerHTML = '<br>'; //removes the previous elements
    const view = document.createElement('div');
    view.className = "card text-white bg-primary m-3";
    view.innerHTML = `<div class="card-header">
                        ${character.name} [<a href="#" id="href-close">X</a>]
                      </div>
                      <div class="card-body">
                        ${JSON.stringify(character)}
                      </div>`;

    const backLink = view.querySelector('#href-close');
    backLink.addEventListener('click', (event) => {
        event.preventDefault();
        render(characters, fetchCharacter);
    });
    container.appendChild(view);
}

function render(characters, fetchCharacter) {
    const container = document.querySelector('#container');
    container.innerHTML = '<br>'; //removes the previous elements
    const list = document.createElement('div');
    // list.style = `display: flex; margin-top: 10%`;
    list.className = `text-center d-flex flex-wrap`;

    characters.forEach(({ name, image, url }, i) => {        
        const item = document.createElement('div');
        item.className = "col card text-white bg-primary flex-fill m-3";
        // item.style="max-width: 18rem";
        item.innerHTML = `<div class="card-header">
                            <a href="${url}" id="href-character-${i}">${name}</a>
                          </div>
                          <div class="card-body">
                            <img style="max-width: 150px" src="${image}" />
                          </div>`;
        list.appendChild(item);

        const link = item.querySelector(`#href-character-${i}`);
        link.addEventListener('click', async (event) => {
            event.preventDefault();
            const character = await fetchCharacter(url);
            renderCharacter(character, characters, fetchCharacter);
        });
    });

    container.appendChild(list);
}

export default { render };
