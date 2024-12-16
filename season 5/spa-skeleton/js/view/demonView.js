async function renderCharacter(character, characters, fetchCharacter) {

    const container = document.querySelector('#container');
    container.innerHTML = '<br>'; //removes the previous elements
    const view = document.createElement('div');
    console.log(character);
    view.className = "card text-white bg-primary m-3";
    view.innerHTML = `<div class="card-header">
                        ${character[0].name} [<a href="#" id="href-close">X</a>]
                      </div>
                      <div class="card-body">
                      <img src="${character[0].image}" />
                      <img src="${character[0].gallery[0]}" />
                      </div>`;

    const backLink = view.querySelector('#href-close');
    backLink.addEventListener('click', (event) => {
        event.preventDefault();
        render(characters, fetchCharacter);
    });
    container.appendChild(view);
}

async function render(characters, fetchCharacter) {
    const container = document.querySelector('#container');
    container.innerHTML = '<br>'; //removes the previous elements
    const list = document.createElement('div');
    // list.style = `display: flex; margin-top: 10%`;
    list.className = `text-center d-flex flex-wrap`;

    characters.forEach(({ name, image, url }, i) => {    
      console.log(url)    
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
          console.log("batata");
            event.preventDefault();
            const response = await fetch(`https://demon-slayer-api.onrender.com/v1/${url}`);
            const character = await response.json();
            console.log(character);
            await renderCharacter(character, characters, fetchCharacter);
        });
    });

    container.appendChild(list);
}

export default { render };
