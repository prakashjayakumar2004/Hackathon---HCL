import logo from './logo.svg';
import './App.css';
import {BrowserRouter, Routes,Route} from "react-router-dom";
import Register from './Register';
import ConfirmPage from './ConfirmPage';
import Home from './Home';
 import Cart from "./Cart";
function App() {
  return (
   <BrowserRouter>
   <Routes>
    <Route path='/' element={<Register/>}></Route>
    <Route path='/home' element={<Home/>}></Route>
    <Route path='/confirmPage' element={<ConfirmPage/>}></Route>
   

<Route path="/cart" element={<Cart />} />
   </Routes>
   
   
   </BrowserRouter>
  );
}

export default App;
