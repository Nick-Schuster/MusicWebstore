import {Component, ElementRef, inject, ViewChild} from '@angular/core';
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
  showAllArticles: boolean =  false;
  @ViewChild('allButton') allButton!: ElementRef<HTMLButtonElement>;
  @ViewChild('lessButton') lessButton!: ElementRef<HTMLButtonElement>;
  constructor(private router: Router)
  {
    this.articleServices = inject(ArticleServices);
  }

  async ngOnInit(){
    this.articles = await this.articleServices.getAllArticlesSize("10");
    this.articles = this.articles.content;
    this.lessButton.nativeElement.style.display = 'none';
  }

  toProductView(id: number){
    this.router.navigate(['/productview',id]);
  }

  async changeSort(){
    const sortInput: HTMLSelectElement | null = document.getElementById('sortInput') as HTMLSelectElement;
    if(this.showAllArticles){
      switch(sortInput.value){
        case 'Sort by price':
          this.articles = await this.articleServices.getAllArticlesSort("price,asc");
          this.articles = this.articles.content;
          break;
          case 'Sort by rating':
            this.articles = await this.articleServices.getAllArticles();
            break;
                default:
                  this.articles = await this.articleServices.getAllArticles();
                  break;
      }
    }
    if(!this.showAllArticles){
      switch(sortInput.value){
        case 'Sort by price':
          this.articles = await this.articleServices.getAllArticlesSizeSort("10","price,asc");
          this.articles = this.articles.content;
          break;
        case 'Sort by rating':
          this.articles = await this.articleServices.getAllArticles();
          this.articles = this.articles.content;
          break;
        default:
          this.articles = await this.articleServices.getAllArticlesSize("10");
          this.articles = this.articles.content;
          break;
      }
    }
  }

  async showAll():Promise<void> {
    this.showAllArticles = true;
    await this.changeSort();
    this.lessButton.nativeElement.style.display = '';
    this.allButton.nativeElement.style.display = 'none';
  }

  async showLess(): Promise<void> {
    this.showAllArticles = false;
    await this.changeSort();
    this.lessButton.nativeElement.style.display = 'none';
    this.allButton.nativeElement.style.display = '';
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
