import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GroceryItem } from './groceryItem';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class GroceryService {
  private apiBase = 'http://localhost:8080/api/grocery';

  constructor(private http: HttpClient) {}

  list(): Observable<GroceryItem[]> {
    return this.http.get<GroceryItem[]>(this.apiBase);
  }

  fromRecipes(ids: number[]): Observable<GroceryItem[]> {
    return this.http.post<GroceryItem[]>(`${this.apiBase}/from-recipes`, ids);
  }

  toggle(id: number): Observable<GroceryItem> {
    return this.http.patch<GroceryItem>(`${this.apiBase}/${id}/toggle`, {});
  }

  remove(id: number) {
    return this.http.delete(`${this.apiBase}/${id}`);
  }

  clearAll() {
    return this.http.delete(this.apiBase);
  }
}