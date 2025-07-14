import {Component, inject} from '@angular/core';
import {Router} from '@angular/router';
import {ArticleServices} from '../shared/article-services';

@Component({
  selector: 'app-basket',
  imports: [],
  templateUrl: './basket.html',
  styleUrl: './basket.css'
})
export class Basket {
  articles:any[] = [];
  private articleServices: ArticleServices;
  constructor(private router: Router) {
    this.articleServices = inject(ArticleServices);
  }
  async ngOnInit() {
    for(let i = 0; i < localStorage.length; i++){
      const key:string | null = localStorage.key(i);

      if(key){
        const value:string |null = localStorage.getItem(key);
        if(value){
          const article:any = await this.articleServices.getArticleById(key)
          this.articles.push(article);
        }
      }
    }
  }

  toProductView(id: number){
    this.router.navigate(['/productview',id]);
  }

  clearBasket(){
    localStorage.clear();
    this.articles.splice(0, this.articles.length);
  }
}
