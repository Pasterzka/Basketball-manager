import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { League } from '../../models/league';
import { Basketball } from '../../services/basketball';

@Component({
  selector: 'app-league-list',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './league-list.html',
  styleUrl: './league-list.css',
})
export class LeagueList implements OnInit{
  leagues: League[] = [];
  newLeague: League = { name: '', country: '', fundingYear: 2024 };
  
  constructor(private service: Basketball,private cdr: ChangeDetectorRef) {}

  ngOnInit() { this.refresh(); }

  refresh() {
    this.service.getAll().subscribe({
      next: (data) => {
        this.leagues = data;
        this.cdr.detectChanges(); 
        console.log('Dane załadowane:', data);
      },
      error: (err) => console.error('Błąd:', err)
    });
  }

  save() {
    this.service.add(this.newLeague).subscribe(() => {
      this.refresh();
      this.newLeague = { name: '', country: '', fundingYear: 2024 };
    });
  }

  remove(id: string) {
    this.service.delete(id).subscribe(() => this.refresh());
  }
}
