import React from 'react';
import { createBrowserRouter, Outlet} from 'react-router-dom';

// Импортируем компоненты страниц, которые будем использовать в маршрутах
// Пока у нас есть только BookList, но позже добавим HomePage, AboutPage и т.д.
import BookListPage from './pages/BookListPage/BookListPage'; // Это будет наша страница со списком книг
// import HomePage from './pages/HomePage/HomePage'; // Закомментировано пока, добавим позже
// import AboutPage from './pages/AboutPage/AboutPage'; // Закомментировано пока, добавим позже

// Корневой макет, который будет содержать общие элементы, такие как навигация или футер
const Layout = () => {
    return (
        <>
            {/* Здесь можно добавить Header, Navbar, Footer, которые будут отображаться на всех страницах */}
            {/* Например: <Header /> <Navbar /> */}
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
        index: true, // Это сделает этот маршрут маршрутом по умолчанию для '/'
        element: <BookListPage />, // Пока что главная страница будет BookListPage
      },
      // {
      //   path: 'home',
      //   element: <HomePage />,
      // },
      // {
      //   path: 'about',
      //   element: <AboutPage />,
      // },
      // {
      //   path: 'books',
      //   element: <BookListPage />,
      // },
      // Здесь можно добавить другие маршруты по мере необходимости
      // {
      //   path: '*', // Маршрут для 404 страницы (Page Not Found)
      //   element: <div>404 - Page Not Found</div>,
      // },
    ],
  },
]);

export default router;