import {Component, inject} from '@angular/core';
import {ArticleServices} from '../shared/article-services';

@Component({
  selector: 'app-home',
  imports: [],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

  articleServices: ArticleServices;
  articles:  any;
  constructor()
  {
    this.articleServices = inject(ArticleServices);
  }

  async ngOnInit(){
    this.articles = await this.articleServices.getAllArticles();
    console.log(this.articles);
  }
}
