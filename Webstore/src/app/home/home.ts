import {Component, inject} from '@angular/core';
import {ArticleServices} from '../shared/article-services';
import {NgOptimizedImage} from '@angular/common';
import {Router} from '@angular/router';

@Component({
  selector: 'app-home',
  imports: [

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
    this.articles = await this.articleServices.getAllArticlesSize("10");
    this.articles = this.articles.content;
  }

  toProductView(id: number){
    this.router.navigate(['/productview',id]);
  }

  async changeSort(){
    const sortInput: HTMLSelectElement | null = document.getElementById('sortInput') as HTMLSelectElement;
    if(sortInput){
      switch(sortInput.value){
        case 'Sort by price':
          this.articles = await this.articleServices.getAllArticlesSizeSort("10","price,asc");
          this.articles = this.articles.content;
          break;
          case 'Sort by rating':
            break;
            case 'Sort by name':
              break;
                default:
                  this.articles = await this.articleServices.getAllArticlesSize("10");
                  break;
      }
    }
  }
}
