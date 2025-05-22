import { useState } from 'react';
import TextHistory from './textHistory.jsx';
import TextBox from './textBox.jsx';
import React from 'react';

function App() {
  const [messageHistory, setMessageHistory] = useState([]); // : [message, sender]

  function newMessage(message) {
    setMessageHistory([...messageHistory, message]);
  }

  return (<div id="app">
    <>
      {<TextHistory messageHistory={messageHistory} />}
      {<TextBox sendMessage={newMessage} />}
    </>
  </div>

  );
}

export default App;