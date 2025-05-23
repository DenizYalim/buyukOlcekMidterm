import { useState, useEffect } from 'react';
import TextHistory from './textHistory.jsx';
import TextBox from './textBox.jsx';
import React from 'react';

function App() {
  const [messageHistory, setMessageHistory] = useState([]); // : [message, sender]

  function newMessage(message) {
    setMessageHistory([...messageHistory, message]);

    const [llmResponse, setLLMResponse] = useState(null);

    const [error, setError] = useState(null);

    useEffect(() => {
      fetch('http://localhost:5000/llm/getResponse?message= hi gpt what is 10 + 200')
        .then((response) => {
          if (!response.ok) {
            throw new Error('Network response was not ok');
          }
        })
        .then((jsonData) => {
          setLLMResponse(jsonData);
          setMessageHistory([...messageHistory, ['AI', llmResponse]])
        })
        .catch((err) => {
          setError(err.message);
        });
    })
  }

function PingGateway() {
  const [message, setMessage] = useState(null);
  const [error, setError] = useState(null);

  useEffect(() => {
    fetch('http://localhost:5000/ping_gateway')
      .then((response) => {
        if (!response.ok) throw new Error('Network error');
        return response.text();
      })
      .then((data) => {
        setMessage(data);
      })
      .catch((error) => {
        console.error('Fetch error:', error);
        setError('Failed to fetch message');
      });
  }, []);

  if (error) return <div>{error}</div>;
  if (message === null) return <div>Loading...</div>;

  return <div>{message}</div>;
}

  return (<div id="app">
    <>
      <PingGateway />
      {<TextHistory messageHistory={messageHistory} />}
      {<TextBox sendMessage={newMessage} />}
    </>
  </div>

  );
}

export default App;