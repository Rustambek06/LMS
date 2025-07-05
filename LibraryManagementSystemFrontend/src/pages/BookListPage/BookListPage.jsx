// src/pages/BookListPage/BookListPage.jsx
import React, { useEffect, useState } from "react";
import { getBooks } from "../../services/api"; // Путь к сервису API

function BookListPage() { // Переименовали компонент в BookListPage
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
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

        fetchBooksData();
    }, []);

    if (loading) return <div>Loading books...</div>;
    if (error) return <div>Error : {error.message}</div>;

    return (
        <div>
            <h1>Books List</h1> {/* Изменил заголовок */}
            {books.length === 0 ? (
                <p>No books found.</p>
            ) : (
                <ul>
                    {books.map(book => (
                        <li key={book.id}>{book.title}</li>
                    ))}
                </ul>
            )}
        </div>
    );
}

export default BookListPage;