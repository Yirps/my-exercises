import demonSlayerService from '../service/demonSlayerService.js';
import demonView from '../view/demonView.js';

export async function init() {
    const characters = await demonSlayerService.fetchCharacters();
    demonView.render(characters, demonSlayerService.fetchCharacter);
}