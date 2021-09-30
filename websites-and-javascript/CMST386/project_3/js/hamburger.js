let isOpenHamburger = false;

let openHamburger = function(){
  let getMenuBar = document.querySelector("#hamburgerlinks");
  let getMenuBarA = document.querySelector("#hamburgerlinks a");

if (isOpenHamburger === false) {
  getMenuBar.style.visibility = "visible";
  getMenuBar.style.display = "block";
  getMenuBar.style.height = "auto";
  isOpenHamburger = true;
}
else if(isOpenHamburger === true) {
  getMenuBar.style.visibility = "hidden";
  getMenuBar.style.display = "none";
  getMenuBar.style.height = "0px";
  isOpenHamburger = false;
}
}
