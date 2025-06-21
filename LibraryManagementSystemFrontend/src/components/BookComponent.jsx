import React, {use, useEffect, useState} from "react";
import axios from "axios";

function BookList() {
    const [books, setBooks] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    useEffect(() => {
        const fetchBooks = async () => {
            try {
                const response = await axios.get('http://localhost:8080/books');
                setBooks(response.data);
                setLoading(false);
            }
            catch(error) {
                setError(error);
                setLoading(false);
            }
        };

        fetchBooks();
    }, []);

    if (loading) return <div>Loading books...</div>;
    if (error) return <div>Error : {error.message}</div>;

    return (
        <div>
            <h1>Books</h1>
            <ul>
                {books.map(book => (
                    <li key={book.id}>{book.title}</li>
                ))}
            </ul>
        </div>
    );
}

export default BookList;