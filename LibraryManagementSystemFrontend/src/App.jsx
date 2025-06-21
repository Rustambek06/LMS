import { useState } from 'react'
import BookList from './components/BookComponent'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="App">
      <header className="App-header">
        <h1>Library Management System</h1>
      </header>
      <main>
        <BookList/>
      </main>
    </div>
  )
}

export default App
