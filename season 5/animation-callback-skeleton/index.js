const box = document.querySelector("#box");
const container = document.querySelector("#container");
let direction = "right";
let wall = false;
function move({ target }) {
  const actualPosition = parseInt(getComputedStyle(target).left);
  if (direction === "right") {
    target.style.left = actualPosition + 1 + "px";
  }
  if (direction === "left") {
    target.style.left = actualPosition - 1 + "px";
  }
  if (direction === null) {
    target.style.left = 0 + "px";
  }
  if (actualPosition === container.clientWidth - box.clientWidth) {
    direction = "left";
    wall = true;
  }
  if(actualPosition === 0 && wall === true) {
    direction = null;
  }
}
box.addEventListener("click", () => {
  setInterval(() => move({target:box}),10);
});
