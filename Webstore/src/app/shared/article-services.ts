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
      headers: { "Content-Type": "application/json" },
    });

    return articles.json();
  }

  async getAllArticlesPaginized(page:string,size:string,sort:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products?page=${page}&size=${size}c`,{
      method: 'GET',
      headers: { "Content-Type": "application/json" },
    });
    return articles.json();
  }

  async getAllArticlesSize(size:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products?size=${size}`,{
      method: 'GET',
      headers: { "Content-Type": "application/json" },
    });
    return articles.json();
  }

  async getAllArticlesSizeSort(size:string, sort:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products?size=${size}&sort=${sort}`,{
      method: 'GET',
      headers: { "Content-Type": "application/json" },
    });
    return articles.json();
  }

  async getAllArticlesSort(sort:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products?sort=${sort}`,{
      method: 'GET',
      headers: { "Content-Type": "application/json" },
    });
    return articles.json();
  }

  async getArticleByName(name:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products/search?name=${name}`,{
      method: 'GET',
      headers: { "Content-Type": "application/json" },
    });
    return articles.json();
  }

  async getArticleById(id:string): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products/${id}`,{
      method: 'GET',
      headers: { "Content-Type": "application/json" },
    });
    return articles.json();
  }

  async postArticle(post: object): Promise<any> {
    const articles: any = await fetch('http://localhost:8080/api/products',{
      method: 'POST',
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(post),
    });
    return articles.json();
  }

  async putArticleById(id:string ,object:object): Promise<any> {
    const articles: any = await fetch(`http://localhost:8080/api/products/${id}`,{
      method: 'PUT',
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(object),
    });
    return articles.json();
  }

}
