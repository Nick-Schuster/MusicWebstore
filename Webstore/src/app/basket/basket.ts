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
    await this.loadBasket()
  }

  toProductView(id: number){
    this.router.navigate(['/productview',id]);
  }

  clearBasket(){
    for(const article of this.articles){
      this.articleServices.putArticleById(article.id,{
        id:article.id,
        name:article.name,
        description:article.description,
        price:article.price,
        inStock:false,
        averageRating:article.averageRating,
        images:article.images,
        reviews:article.reviews,
      })
    }
    localStorage.clear();
    this.articles.splice(0, this.articles.length);
  }

  async removeFromBasket(id: number){
    localStorage.removeItem(id.toString());
    await this.loadBasket()
  }

  async loadBasket(){
    this.articles.splice(0, this.articles.length);
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
}
