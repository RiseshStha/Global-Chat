const globalChatBtn = document.querySelector('#globalChat');
const aboutUsBtn = document.querySelector('#aboutUs');
const profileBtn = document.querySelector('#userAccount');
const logoutBtn = document.querySelector('#logout');
const openGlobal = ()=>{
    globalChatBtn.classList.add('active-room ');
}
const openProfile = ()=>{
    profileBtn.classList.add('active-room');
}
const openAbout = ()=>{
    aboutUsBtn.classList.add('active-room');

}
const logoutbtn = ()=>{
    logoutBtn.classList.add('active-room');

}

globalChatBtn.onclick = () => openGlobal();
aboutUsBtn.onclick = () => openAbout();
profileBtn.onclick = () => openProfile();
logoutBtn.onclick = () => logoutbtn();


