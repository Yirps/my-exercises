import { routes } from './routes.js';

function handlePopState({ state }) {
    const route = state || routes.home;

    setCurrentRoute(route);
    loadController(route.controller);
}

function addEventsToAnchors() {
    const anchors = document.querySelectorAll('a');

    anchors.forEach(anchor =>
        anchor.addEventListener('click', function (event) {
            event.preventDefault();
            navigate(anchor.pathname);
        })
    );
}

async function loadController(controllerName) {
    try {
        const module = await import(`./controller/${controllerName}.js`);
        module.init();
    } catch (err) {
        console.error(err);
    }
}

function setCurrentRoute(route) {
    const { path, controller } = route;

    routes.currentPath.path = path;
    routes.currentPath.controller = controller;
}

function navigate(path, firstLoad = false) {
    if (routes.currentPath.path === path) {
        return;
    }

    const routeKey = Object.keys(routes).find(key => routes[key].path === path);
    const route = routes[routeKey] || routes.home;

    setCurrentRoute(route);

    firstLoad
        ? history.replaceState(route, '', route.path)
        : history.pushState(route, '', route.path);

    loadController(route.controller);
}

function init() {
    console.log("I'm inside the router module and init function");

    const path = window.location.pathname;

    navigate(path, true);
    window.addEventListener('popstate', handlePopState);
    addEventsToAnchors();
}

export default { init };
