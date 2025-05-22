import React from 'react';

function TextHistory({ messageHistory }) {
    if (!messageHistory || messageHistory.length === 0) {
        return <>No messages yet</>;
    }

    return (
        <>
            Chatlog
            {messageHistory.map(([sender, message], index) => (
                <div
                    key={index}
                    style={{
                        textAlign: sender === 'Client' ? 'right' : 'left',
                        margin: '8px 0',
                    }}
                >
                    <strong>{sender}:</strong> {message}
                </div>
            ))}
        </>
    );
}

export default TextHistory;
