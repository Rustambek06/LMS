// src/pages/BookListPage/BookListPage.jsx
import React, { useEffect, useState } from "react";
import { getBooks, createBook, deleteBook } from "../../services/api"; // Путь к сервису API

function BookListPage() { // Переименовали компонент в BookListPage
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const [newBookTitle, setNewBookTitle] = useState('');
    const [newBookAuthor, setNewBookAuthor] = useState('');
    const [newBookYear, setNewBookYear] = useState(null);
    const [addingBook, setAddingBook] = useState(false);

    const [hoveredBookId, setHoveredBookId] = useState(null);
    const [deletingBook, setDeletingBook] = useState(false);

    const fetchBooksData = async () => {
        try {
            const data = await getBooks();
            setBooks(data);
            setLoading(false);
        }
        catch(err) {
            setError(err);
            setLoading(false);
        }
    };

    // --- Функция для обработки отправки формы добавления книги ---
    const handleAddBook = async (e) => {
        e.preventDefault();

        if (!newBookTitle || !newBookAuthor || !newBookYear) {
            alert('Please enter title, author and year.');
            return;
        }

        setAddingBook(true);
        try {
            const bookData = {title: newBookTitle, author: newBookAuthor, year: newBookYear};
            const addedBook = await createBook(bookData);
            
            //Refresh bookList

            setBooks(prevBooks => [...prevBooks, addedBook]);
            setNewBookTitle('');
            setNewBookAuthor('');
            setNewBookYear(null);

            alert('Book added succesfully!');
        } catch (err) {
            console.error('Failed to add book:', err)
            setError(err);
        } finally {
            setAddingBook(false);
        }
    };

    const handleMouseEnter = async (bookId) => {
        setHoveredBookId(bookId);
    };

    const handleMouseLeave = () => {
        setHoveredBookId(null);
    };

    const handleDeleteClick = async (bookId) => {
        setDeletingBook(true);
        console.log(deletingBook);
        try {
            const response = await deleteBook(bookId);
        } catch (err) {
            console.error('Failed to delete book:', err);
            setError(err);
        }
        finally {
            setDeletingBook(false);
        }
        console.log(`Delete button clicked for book ID: ${bookId}`);
    };

    useEffect(() => {
        fetchBooksData();
        console.log(deletingBook);
    }, [addingBook, deletingBook]);

    if (error && !loading && !addingBook) { // Отображаем ошибку, если она есть и не идет загрузка/добавление
        return <div>Error : {error.message}</div>;
    }

    return (
        <div>
            <h1>Books List</h1>

            {/* --- Форма для добавления новой книги --- */}
            <div style={{ marginBottom: '30px', border: '1px solid #ccc', padding: '20px', borderRadius: '8px' }}>
                <h2>Add New Book</h2>
                <form onSubmit={handleAddBook}>
                    <div style={{ marginBottom: '10px' }}>
                        <label htmlFor="bookTitle" style={{ display: 'block', marginBottom: '5px' }}>Title:</label>
                        <input
                            type="text"
                            id="bookTitle"
                            value={newBookTitle}
                            onChange={(e) => setNewBookTitle(e.target.value)}
                            style={{ width: 'calc(100% - 22px)', padding: '10px', borderRadius: '4px', border: '1px solid #ddd' }}
                            disabled={addingBook} // Отключаем поля во время добавления
                        />
                    </div>
                    <div style={{ marginBottom: '10px' }}>
                        <label htmlFor="bookAuthor" style={{ display: 'block', marginBottom: '5px' }}>Author:</label>
                        <input
                            type="text"
                            id="bookAuthor"
                            value={newBookAuthor}
                            onChange={(e) => setNewBookAuthor(e.target.value)}
                            style={{ width: 'calc(100% - 22px)', padding: '10px', borderRadius: '4px', border: '1px solid #ddd' }}
                            disabled={addingBook} // Отключаем поля во время добавления
                        />
                    </div>
                    <div style={{ marginBottom: '10px' }}>
                        <label htmlFor="bookYear" style={{ display: 'block', marginBottom: '5px' }}>Year:</label>
                        <input
                            type="number"
                            id="bookYear"
                            value={newBookYear}
                            onChange={(e) => setNewBookYear(e.target.value)}
                            style={{ width: 'calc(100% - 22px)', padding: '10px', borderRadius: '4px', border: '1px solid #ddd' }}
                            disabled={addingBook} // Отключаем поля во время добавления
                        />
                    </div>
                    <button
                        type="submit"
                        disabled={addingBook} // Отключаем кнопку во время добавления
                        style={{ padding: '10px 20px', backgroundColor: '#007bff', color: 'white', border: 'none', borderRadius: '4px', cursor: 'pointer' }}
                    >
                        {addingBook ? 'Adding Book...' : 'Add Book'}
                    </button>
                </form>
            </div>

            {/* --- Список книг --- */}
            <h2>Existing Books</h2>
            {loading ? ( // Отображаем индикатор загрузки для списка книг
                <div>Loading books...</div>
            ) : (
                books.length === 0 ? (
                    <p>No books found.</p>
                ) : (
                    <ul>
                        {books.map(book => (
                            <li
                            key={book.id}
                            onMouseEnter={() => handleMouseEnter(book.id)}
                            onMouseLeave={handleMouseLeave}
                            style={{ position: 'relative', paddingRight: '60px' }} // Added padding for the button
                            >
                            {book.title} by {book.author || 'Unknown Author'}, {book.year}

                            {/* Render the button only if this book is currently hovered */}
                            {hoveredBookId === book.id && (
                                <button
                                onClick={() => handleDeleteClick(book.id)}
                                style={{
                                    position: 'absolute',
                                    right: '10px',
                                    top: '50%',
                                    transform: 'translateY(-50%)',
                                    background: '#007bff',
                                    color: 'white',
                                    border: 'none',
                                    borderRadius: '4px',
                                    padding: '5px 10px',
                                    cursor: 'pointer'
                                }}
                                >
                                Delete
                                </button>
                            )}
                            </li>
                        ))}
                    </ul>
                )
            )}
        </div>
    );
}

export default BookListPage;