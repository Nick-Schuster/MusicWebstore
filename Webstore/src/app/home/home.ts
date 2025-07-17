import {Component, ElementRef, inject, ViewChild} from '@angular/core';
import {ArticleServices} from '../shared/article-services';
import {NgOptimizedImage} from '@angular/common';
import {Router} from '@angular/router';
import stringComparison from 'string-comparison';

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
  @ViewChild('selectElement') selectElement!: ElementRef<HTMLSelectElement>;
  @ViewChild('inputElement') inputElement!: ElementRef<HTMLInputElement>;
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
    const sortInput  = this.selectElement.nativeElement;
    if(this.showAllArticles){
      switch(sortInput.value){
        case 'Sort by price ascending':
          this.articles = await this.articleServices.getAllArticlesSort("price,asc");
          this.articles = this.articles.content;
          break;
        case 'Sort by price descending':
          this.articles = await this.articleServices.getAllArticlesSort("price,desc");
          this.articles = this.articles.content;
          break;
          default:
            this.articles = await this.articleServices.getAllArticles();
            break;
      }
    }
    if(!this.showAllArticles){
      switch(sortInput.value){
        case 'Sort by price ascending':
          this.articles = await this.articleServices.getAllArticlesSizeSort("10","price,asc");
          this.articles = this.articles.content;
          break;
        case 'Sort by price descending':
          this.articles = await this.articleServices.getAllArticlesSizeSort("10","price,desc");
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


  async search(){
    const input:string = this.inputElement.nativeElement.value;
    if(input  ==  ""){
      await this.changeSort();
      return;
    }
    const allArticles:any  = await this.articleServices.getAllArticles();
    let match:number = 0;
    const resut:any[] =[];
    for(let article of allArticles){
      const name:string = article.name;
      match = stringComparison.jaroWinkler.similarity(input, name);
      if(match  >  0.8){
        resut.push(article);
      }
    }
    this.articles = resut;
  }
}
