import axios from "axios";

const API_BASE_URL = 'http://localhost:8080';

const api = axios.create({
    baseURL: API_BASE_URL, 
    headers: {
        'Content-Type': 'application/json',
        // Здесь можно добавить другие глобвльные звголовки, например, для авторизации 
    },
});

export const getBooks = async () => {
    try {
        const response = await api.get('/books');
        return response.data;
    } catch (error){
        // Здесь можно добавить более сложную обработку ошибок, логирование и т.д.
        console.error('Error fetching books:', error);
        throw error; // Перебрасываем ошибку, чтобы компонент мог ее обработать
    }
}

export const createBook = async (bookData) => {
    try {
        const response = await api.post('/books', bookData);
        return response.data;
    } catch (error) {
        console.error('Error creating book:', error);
        throw error;
    }
} ;

export const deleteBook = async (bookId) => {
    try {
        const response = await api.delete(`/books/${bookId}`);
        return response.data;
    } catch (error) {
        console.error('Error deleting book:', error);
        throw error;
    }
}

// Здесь можно добавлять другие функции для работы с API:
// export const createBook = async (bookData) => { ... }
// export const updateBook = async (id, bookData) => { ... }
// export const deleteBook = async (id) => { ... }