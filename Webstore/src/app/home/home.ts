import {Component, inject} from '@angular/core';
import {ArticleServices} from '../shared/article-services';
import {NgOptimizedImage} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [
    NgOptimizedImage
  ],
  templateUrl: './home.html',
  styleUrl: './home.css'
})
export class Home {

  articleServices: ArticleServices;
  articles:  any;
  constructor(private router: Router)
  {
    this.articleServices = inject(ArticleServices);
  }

  async ngOnInit(){
    this.articles = await this.articleServices.getAllArticles();
  }

  toProductView(id: number){
    this.router.navigate(['/productview',id]);
  }
}
