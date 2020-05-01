import React from 'react'

function App() {
    return (
        <div>
            "Hello"
            <button onClick={(e) => out()}>Log out</button>
        </div>
    )

    function out() {
        localStorage.clear();
        document.location.reload();
    }
}

export default App;