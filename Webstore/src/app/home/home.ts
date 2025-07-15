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
            this.articles = await this.articleServices.getAllArticles();
            break;
            case 'Sort by name':
              break;
                default:
                  this.articles = await this.articleServices.getAllArticlesSize("10");
                  break;
      }
    }
  }

  async showAll():Promise<void> {
    this.articles = await this.articleServices.getAllArticles();
  }

  //Sehr fragwürdiger selbst ausgedachter Suchalgorithmus
  async search(){
    const searchInputElement: HTMLInputElement | null = document.getElementById('searchInput') as HTMLInputElement;
    const searchInput:string = searchInputElement.value;
    if(searchInput.length == 0){
      this.articles = await this.articleServices.getAllArticlesSize("10");
      this.articles = this.articles.content;
      return;
    }
    let selectedArticles: any[] = [];
    this.articles = await this.articleServices.getAllArticles();
    for(const articel of this.articles){
      let match: number  = 0;
      const name:string = articel.name;
      for(let i=0;i < name.length; i++){
        if(searchInput.includes(name[i])){
          match = match + 1;
        }
      }
      match = match / searchInput.length;
      console.log(match);
      if(match > 0.7){
        selectedArticles.push(articel);
      }
    }
    this.articles = selectedArticles;
  }
}
