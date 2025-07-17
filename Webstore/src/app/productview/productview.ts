import {Component, inject} from '@angular/core';
import {ArticleServices} from '../shared/article-services';
import {ActivatedRoute} from '@angular/router';
import Toast from 'typescript-toastify';

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
   isInBasket: string | null = null;
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
     if(this.id  && this.article.inStock) {
       localStorage.setItem(this.id, "true");

       const toast = new Toast({
         position: "bottom-right",
         toastMsg: "Added to basket",
         autoCloseTime: 2000,
         canClose: true,
         showProgress: true,
         pauseOnHover: true,
         pauseOnFocusLoss: true,
         type: "default",
         theme: "light"
       });
     }
     else {
       const toast = new Toast({
         position: "bottom-right",
         toastMsg: "Product not in stock at the moment",
         autoCloseTime: 2000,
         canClose: true,
         showProgress: true,
         pauseOnHover: true,
         pauseOnFocusLoss: true,
         type: "default",
         theme: "light"
       });
     }
   }
}
