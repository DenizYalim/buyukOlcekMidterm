import React, { useState } from 'react';

function TextBox({ sendMessage }) {
  const [message, setMessage] = useState('');

  const submitMessage = (e) => {
    e.preventDefault();
    if (message.trim() === '') return;
    sendMessage( ['Client', message]);
    setMessage('');
  };

  return (
    <form onSubmit={submitMessage}>
      <input
        id="input__text"
        type="text"
        value={message}
        onChange={(e) => setMessage(e.target.value)}
      />
      <button type="submit">Send</button>
    </form>
  );
}

export default TextBox;
