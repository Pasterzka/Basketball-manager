import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/internal/Observable';
import { League } from '../models/league';
import { Team } from '../models/team';

@Injectable({
  providedIn: 'root',
})
export class Basketball {
  private apiUrl = '/api/leagues';

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

  getTeam(leagueId: string, teamId: string): Observable<Team> {
    return this.http.get<Team>(`${this.apiUrl}/${leagueId}/teams/${teamId}`);
  }

  getTeamsByLeague(leagueId: string): Observable<Team[]> {
    return this.http.get<Team[]>(`${this.apiUrl}/${leagueId}/teams`);
  }

  addTeam(leagueId: string, team: Team): Observable<void> {
  return this.http.post<void>(`${this.apiUrl}/${leagueId}/teams`, team);
  }

  updateTeam(leagueId: string, teamId: string, team: Team): Observable<void> {
  return this.http.put<void>(`${this.apiUrl}/${leagueId}/teams/${teamId}`, team);
}

  deleteTeam(leagueId: string, teamId: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${leagueId}/teams/${teamId}`);
  }
}
