import React, { useState } from 'react';

function App() {
  const [inputValue, setInputValue] = useState('');
  const [result, setResult] = useState({});

  const handleSubmit = async (e) => {
    e.preventDefault();

    const response = await fetch('http://localhost:8080/fibonacci', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ n: parseInt(inputValue, 10) }),
    });

    const data = await response.json();
    setResult(data);
  };

  return (
      <div>
        <form onSubmit={handleSubmit}>
          <label>
            Enter a number (n):
            <input
                type="number"
                value={inputValue}
                onChange={(e) => setInputValue(e.target.value)}
            />
          </label>
          <button type="submit">Submit</button>
        </form>

        {result['list'] !== undefined && result['sorted'] &&(
            <div>
              <p>fibonacci list: {result['list'].join(' ')}</p>
              <p>sorted list: {result['sorted'].join(' ')}</p>
            </div>
        )}
      </div>
  );
}

export default App;
