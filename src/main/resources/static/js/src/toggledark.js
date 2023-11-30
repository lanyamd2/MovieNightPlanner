const toggleDark = document.getElementById('toggleDark');
// const toggleDarkLabel = document.getElementById('toggleDarkLabel');
const navbar = document.querySelector('.navbar');
const searchbars = document.getElementsByClassName('searchsection');
console.log('number of search bars =  ' + searchbars.length);
const body = document.querySelector('body');
var links = document.getElementsByTagName("a");
console.log('number of links = ' + links.length);
var navLinks = document.getElementsByClassName('nav-link');
console.log('number of nav-links ' + navLinks.length);
var buttons = document.getElementsByClassName('btn');
console.log('number of buttons = ' + buttons.length);
var dropdownItems = document.getElementsByClassName('dropdown-item');
console.log('number of dropdown-items = ' + dropdownItems.length);

var modalheaders = document.getElementsByClassName('modal-header');
var modalbodies = document.getElementsByClassName('modal-body');
var modalfooters = document.getElementsByClassName('modal-footer');
console.log('number of modal headers = ' + modalheaders.length);
console.log('number of modal bodies = ' + modalbodies.length);
console.log('number of modal footers = ' + modalfooters.length);

var tables = document.getElementsByTagName('table');
console.log('number of tables = ' + tables.length);

const navDropdownMenu = document.getElementById("navbarDropdownMenu");

var faqQuestions = document.getElementsByClassName('accordion-button');
console.log('number of FAQ questions = ' + faqQuestions.length);
var faqAnswers = document.getElementsByClassName('accordion-body');

//day-name
var dayNames = document.getElementsByClassName('day-name');
console.log('number of day names = ' + dayNames.length);
//full-date
var fullDates = document.getElementsByClassName('full-date');
console.log('number of full dates = ' + fullDates.length);


//END OF WORKING ELEMENTS
var justWatchProfileItem = document.getElementById("justWatchProfileItem");
console.log(justWatchProfileItem);
var justWatchLinks = document.getElementsByClassName('jw-offer-label');
console.log('just watch links' + justWatchLinks[0]);

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

function setNavLinkColors() {
    if (navLinks.length > 0) {
        for (var i = 0; i < navLinks.length; i++) {
            if (navLinks[i].href) {
                navLinks[i].style.color = '';
                navLinks[i].style.transition = '.15s';
            }
            navLinks[i].setAttribute("onmouseover", "this.style.color='white'");
            navLinks[i].setAttribute("onmouseout", "this.style.color=''");
        }
    }
}

function setButtonColors() {
    if (buttons.length > 0) {
        for (var i = 0; i < buttons.length; i++) {
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
        for (var i = 0; i < dropdownItems.length; i++) {
            dropdownItems[i].style.color = '#411F50';
            dropdownItems[i].style.transition = '.15s';
            dropdownItems[i].setAttribute("onmouseover", "this.style.color='#411F50'");
            dropdownItems[i].setAttribute("onmouseout", "this.style.color='#411F50'");
        }
    }
}


function setDarkTheme() {
    navbar.style.backgroundImage = 'linear-gradient( #351a40, #1b0d21)';
    navbar.style.transition = '2s';

    if (searchbars.length > 0) {
        for (var i = 0; i < searchbars.length; i++) {
            searchbars[i].style.color = 'white';
            searchbars[i].style.backgroundImage = 'linear-gradient( #a62847,#8a2038)';
        }
    }

    body.style.background = 'black';
    body.style.color = 'white';
    body.style.transition = '2s';
    // toggleDarkLabel.innerHTML="Dark Mode";
    if (navDropdownMenu != null) {
        navDropdownMenu.classList.add('dropdown-menu-dark');
    }
    if (links.length > 0) {
        for (var i = 0; i < links.length; i++) {
            if (links[i].href) {
                links[i].style.color = 'white';
                links[i].style.transition = '.15s';
            }
            links[i].setAttribute("onmouseover", "this.style.color='grey'");
            links[i].setAttribute("onmouseout", "this.style.color='white'");

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
    setNavLinkColors();
    setButtonColors();
    setDropDownItems();
    if (modalheaders.length > 0) {
        for (var i = 0; i < modalheaders.length; i++) {
            modalheaders[i].style.background = 'purple';
        }
    }
    if (modalbodies.length > 0) {
        for (var i = 0; i < modalbodies.length; i++) {
            modalbodies[i].style.background = 'purple';
        }
    }
    if (modalfooters.length > 0) {
        for (var i = 0; i < modalfooters.length; i++) {
            modalfooters[i].style.background = 'purple';
        }
    }
    if (tables.length > 0) {
        //ADDS table-dark list to tables on page
        // for(var i = 0; i<tables.length; i++){
        // tables[i].classList.add('table-dark');
        // }

        var thElements = document.getElementsByTagName('th');
        if (thElements.length > 0) {
            for (var i = 0; i < thElements.length; i++) {
                thElements[i].style.background = 'purple';
                thElements[i].style.color = 'white';
                thElements[i].style.transition = '2s';
            }
        }
        var tdElements = document.getElementsByTagName('td');
        if (tdElements.length > 0) {
            for (var i = 0; i < tdElements.length; i++) {
                tdElements[i].style.background = 'purple';
                tdElements[i].style.color = 'white';
                tdElements[i].style.transition = '2s';
            }
        }
    }
    if (faqQuestions.length > 0) {
        for (var i = 0; i < faqQuestions.length; i++) {
            faqQuestions[i].style.background = 'black';
            faqQuestions[i].style.color = 'white';
            faqQuestions[i].style.transition = '2s';
        }
    }
    if (faqAnswers.length > 0) {
        for (var i = 0; i < faqAnswers.length; i++) {
            faqAnswers[i].style.background = 'purple';
            faqAnswers[i].style.color = 'white';
            faqAnswers[i].style.transition = '2s';
        }
    }

    if (dayNames.length > 0) {
        for (var i = 0; i < dayNames.length; i++) {
            dayNames[i].style.color = 'white';
            dayNames[i].style.transition = '2s';

        }
    }

    if (fullDates.length > 0) {
        for (var i = 0; i < fullDates.length; i++) {
            fullDates[i].style.color = 'white';
            fullDates[i].style.transition = '2s';

        }
    }


    //END OF WORKING setDarkTheme() at the moment

    if (justWatchProfileItem != null) {
        justWatchProfileItem.setAttribute("data-theme", "dark");
        console.log(justWatchProfileItem);
        console.log(justWatchProfileItem.getAttribute("data-theme"));
        console.log(justWatchProfileItem.outerHTML);
        console.log(location.href);


        // justWatchProfileItem.style.transition='2s';
        // $(document).ready(function (){
        //     console.log(window.location.href);
        //     // $("#justWatchProfileItem").load(window.location.href);
        // });


        // $(document).ready(function(){
        //     var counter = 9;
        //     window.setInterval(function(){
        //         counter = counter - 3;
        //         if(counter>=0){
        //             document.getElementById('off').innerHTML=counter;
        //             console.log('counter has switched')
        //         }
        //         if (counter===0){
        //             counter=9;
        //         }
        //         $("#justWatchProfileItem").load(window.location.href);
        //         console.log('here');
        //         // $("#Offers").load(" #Offers > *");
        //     }, 3000);
        // });
        $(document).ready(function(){
            var counter = 9;
            window.setInterval(function(){
                counter = counter - 3;
                if(counter>=0){
                    document.getElementById('off').innerHTML=counter;
                }
                if (counter===0){
                    counter=9;
                }
                $("#Offers").load(window.location.href);
            }, 3000);
        });
        // ('#justWatchProfileItem').load(location.href);
        // document.getElementById('justWatchProfileItem').outerHTML = justWatchProfileItem.outerHTML;
    }

}

function setLightTheme() {
    navbar.style.backgroundImage = 'linear-gradient( #512763, #411F50)';
    navbar.style.transition = '2s';

    if (searchbars.length > 0) {
        for (var i = 0; i < searchbars.length; i++) {
            searchbars[i].style.color = 'white';
            searchbars[i].style.backgroundImage = 'linear-gradient( #e33661,#ba294d)';
        }
    }


    body.style.background = '';
    body.style.color = '#411F50';
    body.style.transition = '2s';
    // toggleDarkLabel.innerHTML="Light Mode";
    if (navDropdownMenu != null) {
        navDropdownMenu.classList.remove('dropdown-menu-dark');
    }
    if (links.length > 0) {
        for (var i = 0; i < links.length; i++) {
            if (links[i].href) {
                links[i].style.color = '#411F50';
                links[i].style.transition = '.15s';
            }
            links[i].setAttribute("onmouseover", "this.style.color='purple'");
            links[i].setAttribute("onmouseout", "this.style.color='#411F50'");

            // changeLinkHoverColor = lightLinkHover;
            // changeLinkNormalColor = lightLinkNormal;
        }
    }
    setNavLinkColors();
    setButtonColors();
    setDropDownItems();
    if (modalheaders.length > 0) {
        for (var i = 0; i < modalheaders.length; i++) {
            modalheaders[i].style.background = 'white';
        }
    }
    if (modalbodies.length > 0) {
        for (var i = 0; i < modalbodies.length; i++) {
            modalbodies[i].style.background = 'white';
        }
    }
    if (modalfooters.length > 0) {
        for (var i = 0; i < modalfooters.length; i++) {
            modalfooters[i].style.background = 'white';
        }
    }
    if (tables.length > 0) {
        //REMOVES table-dark class from tables on page
        // for(var i = 0; i<tables.length; i++){
        // tables[i].classList.remove('table-dark');
        // }

        var thElements = document.getElementsByTagName('th');
        if (thElements.length > 0) {
            for (var i = 0; i < thElements.length; i++) {
                thElements[i].style.background = '';
                thElements[i].style.color = '#411F50';
                thElements[i].style.transition = '2s';
            }
        }
        var tdElements = document.getElementsByTagName('td');
        if (tdElements.length > 0) {
            for (var i = 0; i < tdElements.length; i++) {
                tdElements[i].style.background = '';
                tdElements[i].style.color = '#411F50';
                tdElements[i].style.transition = '2s';
            }
        }
    }
    if (faqQuestions.length > 0) {
        for (var i = 0; i < faqQuestions.length; i++) {
            faqQuestions[i].style.background = 'purple';
            faqQuestions[i].style.color = 'white';
            faqQuestions[i].style.transition = '2s';
        }
    }
    if (faqAnswers.length > 0) {
        for (var i = 0; i < faqAnswers.length; i++) {
            faqAnswers[i].style.background = 'white';
            faqAnswers[i].style.color = '#411F50';
            faqAnswers[i].style.transition = '2s';
        }
    }
    if (dayNames.length > 0) {
        for (var i = 0; i < dayNames.length; i++) {
            dayNames[i].style.color = '#411F50';
            dayNames[i].style.transition = '2s';

        }
    }
    if (fullDates.length > 0) {
        for (var i = 0; i < fullDates.length; i++) {
            fullDates[i].style.color = '#411F50';
            fullDates[i].style.transition = '2s';

        }
    }


    //END OF WORKING setLightTheme() at the moment

    if (justWatchProfileItem != null) {
        justWatchProfileItem.setAttribute("data-theme", "light");
        console.log(justWatchProfileItem);
        console.log(justWatchProfileItem.getAttribute("data-theme"));
        console.log(justWatchProfileItem.outerHTML);
        // $(document).ready(function (){
        //     $("#justWatchProfileItem").load(window.location.href + " #justWatchProfileItem");
        // });
        // ('#justWatchProfileItem').load(location.href);
        // document.getElementById('justWatchProfileItem').outerHTML = justWatchProfileItem.outerHTML;
    }


}
//
// if(toggleDark != null){
//     var isChecked = toggleDark.checked; // checks the current value of the dark theme toggle switch, true if dark mode is on
//     console.log(isChecked);
//     if (toggleDark.checked) {
//         setDarkTheme();
//     } else {
//         setLightTheme();
//     }
// }
if(toggleDark!=null) {
    toggleDark.addEventListener("change", function () {
        // isChecked = !isChecked;
        console.log(toggleDark.checked);
        console.log(toggleDark.textContent);
        // console.log(toggleDark.innerHTML);

        // check if user has already set a preference before
        //if user is logged in check if they've ever set a preference
        // if user has not set a preference before or is not logged in use their system theme
        //allow user to switch theme preference on their own and save the preference
        //if user is logged in preference needs to be saved and associated with their userid / login
        //if user is not logged in then the preference just needs to be saved within the session so it doesnt change when going to a new page or reloading the page

        if (toggleDark.checked) {
            setDarkTheme();
        } else {
            setLightTheme();
        }
    });
}
// toggle.click();