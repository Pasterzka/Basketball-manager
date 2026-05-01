import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { Team } from '../../models/team';
import { Basketball } from '../../services/basketball';
import { iif } from 'rxjs/internal/observable/iif';

@Component({
  selector: 'app-team-edit',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './team-edit.html',
  styleUrl: './team-edit.css',
})
export class TeamEdit implements OnInit{
  @Input() id!: string; 
  @Input() leagueId!: string;
  team?: Team;

  constructor(
    private service: Basketball,
    private router: Router,
    private cdr: ChangeDetectorRef
  ) {}

  ngOnInit(): void {
    console.log('Edycja drużyny o ID:', this.id);
    if (this.leagueId && this.id) {
    this.service.getTeam(this.leagueId, this.id).subscribe({
      next: (data) => {
        this.team = data;
        if(this.team) this.team.leagueId = this.leagueId;
        this.cdr.detectChanges();
      },
      error: (err) => console.error('Błąd podczas ładowania drużyny:', err)
    });
  }
  }

  updateTeam(): void {
    if (this.team && this.id) {
    this.service.updateTeam(this.leagueId, this.id, this.team).subscribe({
      next: () => {
        console.log('Drużyna zaktualizowana pomyślnie!');
        this.router.navigate(['/edit', this.leagueId]);
      },
      error: (err) => console.error('Błąd aktualizacji:', err)
    });
    }
  }
}
