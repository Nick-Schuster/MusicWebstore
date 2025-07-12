import {Component, inject} from '@angular/core';
import {ArticleServices} from '../shared/article-services';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-productview',
  imports: [],
  templateUrl: './productview.html',
  styleUrl: './productview.css'
})
export class Productview {
  article: any;
   private articleServices: ArticleServices;
   id: string| null=null;
   constructor(private route: ActivatedRoute) {
     this.articleServices = inject(ArticleServices);
   }
   async ngOnInit(){
     this.route.paramMap.subscribe(async paramMap => {
       this.id = paramMap.get('id');
       if (this.id) {
         this.article = await this.articleServices.getArticleById(this.id);
       }
     });
   }

   addToBasket(){
     if(this.id){
       localStorage.setItem(this.id,"true");
     }
   }
}
