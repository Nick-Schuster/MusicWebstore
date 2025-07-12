import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ArticleServices {
  constructor() {

  }

  async getAllArticles(): Promise<any> {
    const articles: any = await fetch('http://localhost:8080/api/products/all',{
      method: 'GET',
    });
    return articles.json();
  }

  async getAllArticlesPaginized(page:string,size:string,sort:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products?page=${page}&size=${size}&sort=${sort}`,{
      method: 'GET',
    });
    return articles.json();
  }

  async getArticleByName(name:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products/search?name=${name}`,{
      method: 'GET',
    });
    return articles.json();
  }

  async getArticleById(id:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products/${id}`,{
      method: 'GET',
    });
    return articles.json();
  }

  async postArticle(post: object): Promise<any> {
    const articles: any = await fetch('http://localhost:8080/api/products',{
      method: 'POST',
      body: JSON.stringify(post),
    });
    return articles.json();
  }

  async putArticleById(id:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products/${id}`,{
      method: 'GET',
    });
    return articles.json();
  }
}
