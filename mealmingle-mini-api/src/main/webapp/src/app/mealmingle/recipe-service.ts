import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Recipe } from './recipe';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {
  private apiBase = 'http://localhost:8080/api/recipes';

  constructor(private http: HttpClient) {}

  list(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.apiBase);
  }

  get(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.apiBase}/${id}`);
  }

  create(r: Recipe): Observable<Recipe> {
    return this.http.post<Recipe>(this.apiBase, r);
  }

  update(id: number, r: Recipe): Observable<Recipe> {
    return this.http.put<Recipe>(`${this.apiBase}/${id}`, r);
  }

  delete(id: number) {
    return this.http.delete(`${this.apiBase}/${id}`);
  }
}