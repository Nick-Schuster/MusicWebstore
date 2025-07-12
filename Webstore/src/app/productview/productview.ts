import {Component, inject} from '@angular/core';
import {ArticleServices} from '../shared/article-services';

@Component({
  selector: 'app-productview',
  imports: [],
  templateUrl: './productview.html',
  styleUrl: './productview.css'
})
export class Productview {
  private articleServices: ArticleServices;
  constructor() {
    this.articleServices = inject(ArticleServices);
  }
  
}
