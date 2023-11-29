const toggleDark = document.getElementById('toggleDark');
// const toggleDarkLabel = document.getElementById('toggleDarkLabel');
const body = document.querySelector('body');
const navDropdownMenu=document.getElementById("navbarDropdownMenu");

var isChecked = toggleDark.checked; // checks the current value of the dark theme toggle switch
console.log(isChecked);

var links = document.getElementsByTagName("a");
console.log(links.length);

//was placed above var links declaration when uncommented
// let changeLinkHoverColor;
// let changeLinkNormalColor;


// function getLinkHoverColors(){
//     changeLinkHoverColor();
// }
//
// function getLinkNormalColors(){
//     changeLinkNormalColor();
// }
//
// function lightLinkHover(){
//     link.style.color='red';
// }
//
// function lightLinkNormal(){
//     link.style.color='';
// }
// function darkLinkHover(){
//     link.style.color='white';
// }
//
// function  darkLinkNormal(){
//     link.style.color='grey';
// }
//
//
// if(links.length > 0){
//     for(var i = 0; i < links.length; i++){
//         var link = links[i];
//         links[i].addEventListener("mouseover", getLinkHoverColors);
//         links[i].addEventListener("mouseout", getLinkNormalColors);
//     }
// }

function setDarkTheme(){
    body.style.background = 'black';
    body.style.color = 'white';
    body.style.transition = '2s';
    // toggleDarkLabel.innerHTML="Dark Mode";
    if(navDropdownMenu != null){
        navDropdownMenu.classList.add('dropdown-menu-dark');
    }
    if(links.length > 0){
        for(var i=0; i < links.length; i++){
            if(links[i].href){
                links[i].style.color = 'grey';
                links[i].style.transition='.15s';
            }
            links[i].setAttribute("onmouseover","this.style.color='white'");
            links[i].setAttribute("onmouseout","this.style.color='grey'");

            // changeLinkHoverColor = darkLinkHover;
            // changeLinkNormalColor = darkLinkNormal;
            // var link = links[i];
            // links[i].addEventListener("mouseover", function () {
            //     link.style.color='white';
            // })
            // links[i].addEventListener("mouseout", function () {
            //     link.style.color='grey';
            // })
        }
    }
}

function setLightTheme(){
    body.style.background = '';
    body.style.color = '';
    body.style.transition = '2s';
    // toggleDarkLabel.innerHTML="Light Mode";
    if(navDropdownMenu != null){
        navDropdownMenu.classList.remove('dropdown-menu-dark');
    }
    if(links.length > 0){
        for(var i=0; i < links.length; i++){
            if(links[i].href){
                links[i].style.color = '';
                links[i].style.transition='.15s';
            }
            links[i].setAttribute("onmouseover","this.style.color='yellow'");
            links[i].setAttribute("onmouseout","this.style.color=''");

            // changeLinkHoverColor = lightLinkHover;
            // changeLinkNormalColor = lightLinkNormal;
        }
    }
}


if(isChecked){

    setDarkTheme();
}else {
    setLightTheme();
}
toggleDark.addEventListener("change", function (){
    isChecked = !isChecked;
    console.log(isChecked)

    // check if user has already set a preference before
    //if user is logged in check if they've ever set a preference
    // if user has not set a preference before or is not logged in use their system theme
    //allow user to switch theme preference on their own and save the preference
    //if user is logged in preference needs to be saved and associated with their userid / login
    //if user is not logged in then the preference just needs to be saved within the session so it doesnt change when going to a new page or reloading the page

    if(isChecked){
        setDarkTheme();
    }else{
        setLightTheme();
    }
});