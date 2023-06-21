const globalChatBtn = document.querySelector('#globalChat');
const pubgBtn = document.querySelector('#pubg');
const freefireBtn = document.querySelector('#freefire');
const valorentBtn = document.querySelector('#valorent');
const chatHeader = document.querySelector('#chatHeader');
const greetHeader = document.querySelector('#greetHeader');
const chatMessage = document.querySelector('.chat-message');
const chatInputForm = document.querySelector('#chat-input-form');
const inputs = document.querySelector('.inputBox');
const container = document.querySelector('.container');
const userAccBtn = document.querySelector('#userAcc');
const menuBtn = document.querySelector('#check-btn');
const leftBar = document.querySelector('.leftBar');
const menuClose = document.querySelector('.menu-close');
const closeBtn = document.querySelector('#close-icon');
const modal = document.getElementById("data-modal");


// Creating methods for displaying or getting messages
const createChatMessageElements = (message) => `
<div class="message-box ${message.sender === "risesh" ? 'right grey-bg':'left'}">
<div class="message-sender">${message.sender}</div>
<div class="message-text">${message.text}</div>
<div class="message-timestamp">${message.timestamp}</div>
</div>
`

let messageSender = "risesh";
const updateMessageSender = (name)=>{
    messageSender=name;
    inputs.placeholder = `Type here ${messageSender}`;
}

const sendMessage = (e) => {
    e.preventDefault(); // preventing screen or page from refreshing.
    console.log(inputs.value)
    const timestamp = new Date().toLocaleString('en-US',{hour:'numeric', minute:'numeric', hour12:true})
    const message = {
        sender: messageSender,
        text: inputs.value,
        timestamp,
    }
    //adding message to message container
    chatMessage.innerHTML+=createChatMessageElements(message);
    chatInputForm.reset(); // to clear the input fields after sending the message
    container.scrollTop = container.scrollHeight;
}
container.scrollTop = container.scrollHeight;
chatInputForm.addEventListener('submit', sendMessage);