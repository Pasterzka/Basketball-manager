import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { League } from '../models/league';

@Injectable({
  providedIn: 'root',
})
export class Basketball {
  private apiUrl = 'http://localhost:8080/leagues';

  constructor(private http: HttpClient) {}

  getAll(): Observable<League[]> { 
    return this.http.get<League[]>(this.apiUrl); 
  }

  getOne(id: string): Observable<League> { 
    return this.http.get<League>(`${this.apiUrl}/${id}`); 
  }

  add(league: League): Observable<void> { 
    return this.http.post<void>(this.apiUrl, league); 
  }
  
  update(id: string, league: League): Observable<void> { 
    return this.http.put<void>(`${this.apiUrl}/${id}`, league); 
  }

  delete(id: string): Observable<void> { 
    return this.http.delete<void>(`${this.apiUrl}/${id}`); 
  }
}
