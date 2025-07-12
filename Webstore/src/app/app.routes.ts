import { Routes } from '@angular/router';
import{Reviews}  from  './reviews/reviews'
import {Home} from './home/home';
import {Productview} from './productview/productview';
import {Register} from './register/register';
import {Login} from './login/login';
import {Basket} from './basket/basket';

export const routes: Routes = [
  {path: '', component: Home},
  {path: 'reviews',component: Reviews},
  {path: 'productview', component: Productview},
  {path: 'register', component: Register},
  {path: 'login', component: Login},
  {path: 'basket', component: Basket},
];
