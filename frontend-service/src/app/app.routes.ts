import { Routes } from '@angular/router';
import { LeagueList } from './components/league-list/league-list';
import { LeagueEdit } from './components/league-edit/league-edit';
import { TeamEdit } from './components/team-edit/team-edit';

export const routes: Routes = [
    { path: '', component: LeagueList, title: 'Lista Lig' },
    { path: 'edit/:id', component: LeagueEdit, title: 'Edycja Ligi' },
    { path: 'league/edit/:leagueId/team-edit/:id', component: TeamEdit },
    { path: '**', redirectTo: '' }
];

