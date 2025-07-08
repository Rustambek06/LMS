import React from 'react';
import { createBrowserRouter, Outlet} from 'react-router-dom';

// Импортируем компоненты страниц, которые будем использовать в маршрутах
import HomePage from './pages/HomePage/HomePage'; // Это будет  наша главная страница
import BookListPage from './pages/BookListPage/BookListPage'; // Это будет наша страница со списком книг
// import AboutPage from './pages/AboutPage/AboutPage'; // Закомментировано пока, добавим позже

// Корневой макет, который будет содержать общие элементы, такие как навигация или футер
const Layout = () => {
    return (
        <>
            {/* Здесь можно добавить Header, Navbar, Footer, которые будут отображаться на всех страницах */}
            {/* Например: добавим простую навигацию для примера */}
            <nav style={{ padding: '10px', background: '#f0f0f0', marginBottom: '20px' }}>
              <ul style={{ listStyle: 'none', padding: 0, margin: 0, display: 'flex', gap: '15px' }}>
                <li><a href="/">Home</a></li> {/* Ссылка на главную */}
                <li><a href="/books">Books</a></li> {/* Ссылка на список книг */}
              </ul>
            </nav>
            <Outlet /> {/* Это место, где будут рендериться компоненты страниц */}
            {/* Например: <Footer /> */}
        </>
    );
};

const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />, // Используем Layout как корневой элемент
    children: [
      {
        index: true, // Главная страница теперь HomePage
        element: <HomePage />,
      },
      {
        path: 'books',
        element: <BookListPage />,
      },
      {
        path: '*',
        element: <div>404 - Page Not Found</div>,
      },
    ],
  },
]);

export default router;