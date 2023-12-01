let toggleDark = document.getElementById('toggleDark');
let navbar = document.querySelector('.navbar');
let searchbars = document.getElementsByClassName('searchsection');
// console.log('number of search bars =  ' + searchbars.length);
let body = document.querySelector('body');
let links = document.getElementsByTagName("a");
// console.log('number of links = ' + links.length);
let navLinks = document.getElementsByClassName('nav-link');
// console.log('number of nav-links ' + navLinks.length);
let buttons = document.getElementsByClassName('btn');
// console.log('number of buttons = ' + buttons.length);
let dropdownItems = document.getElementsByClassName('dropdown-item');
// console.log('number of dropdown-items = ' + dropdownItems.length);

let modalheaders = document.getElementsByClassName('modal-header');
let modalbodies = document.getElementsByClassName('modal-body');
let modalfooters = document.getElementsByClassName('modal-footer');
let modalContentRows = document.getElementsByClassName('modal-content row');
let rowItems = document.getElementsByClassName("row-item");
console.log("number of row-items = " + rowItems.length);
// console.log("number of rows inside modal-content = "+modalContentRows.length);
// console.log('number of modal headers = ' + modalheaders.length);
// console.log('number of modal bodies = ' + modalbodies.length);
// console.log('number of modal footers = ' + modalfooters.length);

let tables = document.getElementsByTagName('table');
// console.log('number of tables = ' + tables.length);

let navDropdownMenu = document.getElementById("navbarDropdownMenu");

let faqQuestions = document.getElementsByClassName('accordion-button');
// console.log('number of FAQ questions = ' + faqQuestions.length);
let faqAnswers = document.getElementsByClassName('accordion-body');

//day-name
let dayNames = document.getElementsByClassName('day-name');
// console.log('number of day names = ' + dayNames.length);
//full-date
let fullDates = document.getElementsByClassName('full-date');
// console.log('number of full dates = ' + fullDates.length);


//END OF WORKING ELEMENTS

function setNavLinkColors() {
    if (navLinks.length > 0) {
        for (let i = 0; i < navLinks.length; i++) {
            if (navLinks[i].href) {
                navLinks[i].style.color = '';
                // navLinks[i].style.transition = '.15s';
            }
            navLinks[i].setAttribute("onmouseover", "this.style.color='white'");
            navLinks[i].setAttribute("onmouseout", "this.style.color=''");
        }
    }
}

function setButtonColors() {
    if (buttons.length > 0) {
        for (let i = 0; i < buttons.length; i++) {
            // if (buttons[i].href) {
            //     buttons[i].style.color = '';
            //     buttons[i].style.transition = '.15s';
            // }
            buttons[i].style.color = 'white';
            buttons[i].setAttribute("onmouseover", "this.style.color='white'");
            buttons[i].setAttribute("onmouseout", "this.style.color='white'");
        }
    }
}

function setDropDownItems() {
    if (dropdownItems.length > 0) {
        for (let i = 0; i < dropdownItems.length; i++) {
            dropdownItems[i].style.color = '#411F50';
            // dropdownItems[i].style.transition = '.15s';
            dropdownItems[i].setAttribute("onmouseover", "this.style.color='#411F50'");
            dropdownItems[i].setAttribute("onmouseout", "this.style.color='#411F50'");
        }
    }
}

function setDarkTheme() {
    navbar.style.backgroundImage = 'linear-gradient(rgb(57, 0, 80), rgb(27, 13, 33))';

    // navbar.style.transition = '2s';

    if (searchbars.length > 0) {
        for (let i = 0; i < searchbars.length; i++) {
            searchbars[i].style.color = 'white';
            searchbars[i].style.backgroundImage = 'linear-gradient(rgb(169, 8, 48), rgb(95, 6, 26))';
        }
    }

    body.style.background = 'black';
    body.style.color = 'white';
    // body.style.transition = '2s';
    // toggleDarkLabel.innerHTML="Dark Mode";
    if (navDropdownMenu != null) {
        navDropdownMenu.classList.add('dropdown-menu-dark');
    }
    if (links.length > 0) {
        for (let i = 0; i < links.length; i++) {
            if (links[i].href) {
                links[i].style.color = '#E5E4E2';
                // links[i].style.transition = '.15s';
            }
            links[i].setAttribute("onmouseover", "this.style.color='white'");
            links[i].setAttribute("onmouseout", "this.style.color='#E5E4E2'");
        }
    }
    setNavLinkColors();
    setButtonColors();
    setDropDownItems();
    if (modalheaders.length > 0) {
        for (let i = 0; i < modalheaders.length; i++) {
            modalheaders[i].style.background = '#411F50';

        }
    }
    if (modalbodies.length > 0) {
        for (let i = 0; i < modalbodies.length; i++) {
            modalbodies[i].style.background = 'purple';
        }
    }
    if (modalfooters.length > 0) {
        for (let i = 0; i < modalfooters.length; i++) {
            modalfooters[i].style.background = 'purple';
        }
    }
    // if(modalContentRows.length > 0){
    //     for(let i = 0; i < modalContentRows.length; i++){
    //         modalContentRows[i].style.background ='purple';
    //         modalContentRows[i].style.color ='white';
    //         let descendentsOfMCR = modalContentRows.getElementsByTagName('*');
    //         if(descendentsOfMCR.length > 0){
    //             for(let j = 0; j < descendentsOfMCR.length; j++){
    //                 descendentsOfMCR[j].style.background="purple";
    //                 modalContentRows[i].style.color ='white';
    //             }
    //         }
    //
    //     }
    // }
    if(rowItems.length > 0){
        for(let i=0; i < rowItems.length; i++){
            rowItems[i].style.background = 'purple';
            rowItems[i].style.color = 'white';
        }
    }
    if (tables.length > 0) {
        //ADDS table-dark list to tables on page
        // for(var i = 0; i<tables.length; i++){
        // tables[i].classList.add('table-dark');
        // }

        let thElements = document.getElementsByTagName('th');
        if (thElements.length > 0) {
            for (let i = 0; i < thElements.length; i++) {
                thElements[i].style.background = 'black';
                thElements[i].style.color = 'white';
                // thElements[i].style.transition = '2s';
            }
        }
        let tdElements = document.getElementsByTagName('td');
        if (tdElements.length > 0) {
            for (let i = 0; i < tdElements.length; i++) {
                tdElements[i].style.background = 'black';
                tdElements[i].style.color = 'white';
                // tdElements[i].style.transition = '2s';
            }
        }
    }
    if (faqQuestions.length > 0) {
        for (let i = 0; i < faqQuestions.length; i++) {
            faqQuestions[i].style.background = '#411F50';
            faqQuestions[i].style.color = 'white';
            // faqQuestions[i].setAttribute("--bs-accordion-active-bg", "#edcfef");

            // edcfef
            // faqQuestions[i].style.transition = '2s';
        }
    }

    if (faqAnswers.length > 0) {
        for (let i = 0; i < faqAnswers.length; i++) {
            faqAnswers[i].style.background = 'black';
            faqAnswers[i].style.color = 'white';
            // faqAnswers[i].style.transition = '2s';
        }
    }
    if (dayNames.length > 0) {
        for (let i = 0; i < dayNames.length; i++) {
            dayNames[i].style.color = 'white';
            // dayNames[i].style.transition = '2s';
        }
    }
    if (fullDates.length > 0) {
        for (let i = 0; i < fullDates.length; i++) {
            fullDates[i].style.color = 'white';
            // fullDates[i].style.transition = '2s';
        }
    }
    //END OF WORKING setDarkTheme() at the moment
}

function setLightTheme() {
    navbar.style.backgroundImage = 'linear-gradient( #512763, #411F50)';
    // navbar.style.transition = '2s';

    if (searchbars.length > 0) {
        for (let i = 0; i < searchbars.length; i++) {
            searchbars[i].style.color = 'white';
            searchbars[i].style.backgroundImage = 'linear-gradient( #e33661,#ba294d)';
        }
    }

    body.style.background = '';
    body.style.color = '#411F50';
    // body.style.transition = '2s';
    // toggleDarkLabel.innerHTML="Light Mode";
    if (navDropdownMenu != null) {
        navDropdownMenu.classList.remove('dropdown-menu-dark');
    }
    if (links.length > 0) {
        for (let i = 0; i < links.length; i++) {
            if (links[i].href) {
                links[i].style.color = '#411F50';
                // links[i].style.transition = '.15s';
            }
            links[i].setAttribute("onmouseover", "this.style.color='purple'");
            links[i].setAttribute("onmouseout", "this.style.color='#411F50'");
        }
    }
    setNavLinkColors();
    setButtonColors();
    setDropDownItems();
    if (modalheaders.length > 0) {
        for (let i = 0; i < modalheaders.length; i++) {
            modalheaders[i].style.background = '#411F50';
            modalheaders[i].style.color = 'white';

        }
    }
    if (modalbodies.length > 0) {
        for (let i = 0; i < modalbodies.length; i++) {
            modalbodies[i].style.background = 'white';
        }
    }
    if (modalfooters.length > 0) {
        for (let i = 0; i < modalfooters.length; i++) {
            modalfooters[i].style.background = 'white';
        }
    }
    // if(modalContentRows.length > 0){
    //     for(let i = 0; i < modalContentRows.length; i++){
    //         modalContentRows[i].style.background ='white';
    //         modalContentRows[i].style.color ='#411F50';
    //         let descendentsOfMCR = modalContentRows.getElementsByTagName('*');
    //         if(descendentsOfMCR.length > 0){
    //             for(let j = 0; j < descendentsOfMCR.length; j++){
    //                 descendentsOfMCR[j].style.background="white";
    //                 modalContentRows[i].style.color ='#411F50';
    //             }
    //         }
    //     }
    // }
    if(rowItems.length > 0){
        for(let i=0; i < rowItems.length; i++){
            rowItems[i].style.background = 'white';
            rowItems[i].style.color = '#411F50';
        }
    }
    if (tables.length > 0) {
        //REMOVES table-dark class from tables on page
        // for(let i = 0; i<tables.length; i++){
        // tables[i].classList.remove('table-dark');
        // }
        let thElements = document.getElementsByTagName('th');
        if (thElements.length > 0) {
            for (let i = 0; i < thElements.length; i++) {
                thElements[i].style.background = '';
                thElements[i].style.color = '#411F50';
                // thElements[i].style.transition = '2s';
            }
        }
        let tdElements = document.getElementsByTagName('td');
        if (tdElements.length > 0) {
            for (let i = 0; i < tdElements.length; i++) {
                tdElements[i].style.background = '';
                tdElements[i].style.color = '#411F50';
                // tdElements[i].style.transition = '2s';
            }
        }
    }
    if (faqQuestions.length > 0) {
        for (let i = 0; i < faqQuestions.length; i++) {
            faqQuestions[i].style.background = '#edcfef';
            faqQuestions[i].style.color = '#411F50';
            // faqQuestions[i].style.transition = '2s';
        }
    }
    if (faqAnswers.length > 0) {
        for (let i = 0; i < faqAnswers.length; i++) {
            faqAnswers[i].style.background = 'white';
            faqAnswers[i].style.color = '#411F50';
            // faqAnswers[i].style.transition = '2s';
        }
    }
    if (dayNames.length > 0) {
        for (let i = 0; i < dayNames.length; i++) {
            dayNames[i].style.color = '#411F50';
            // dayNames[i].style.transition = '2s';
        }
    }
    if (fullDates.length > 0) {
        for (let i = 0; i < fullDates.length; i++) {
            fullDates[i].style.color = '#411F50';
            // fullDates[i].style.transition = '2s';

        }
    }
    //END OF WORKING setLightTheme() at the moment
}
setLightTheme();
if (toggleDark != null) {
    let isChecked = toggleDark.checked; // checks the current value of the dark theme toggle switch, true if dark mode is on
    console.log(isChecked);
    if (isChecked) {
        setDarkTheme();
    } else {
        setLightTheme();
    }
}

function clickToggleDarkFn(event){
    event.preventDefault();
    const toggle = event.currentTarget;
    if(toggle.checked===true){
        toggle.value="on";
    }
    if(toggle.checked===false){
        toggle.value="off";
    }
    event.currentTarget.closest('#toggleDarkForm').submit();
}
