import { Routes } from '@angular/router';
import{Reviews}  from  './reviews/reviews'
import {Home} from './home/home';

export const routes: Routes = [
  {path: '', component: Home},
  {path: 'reviews',component: Reviews},
];
