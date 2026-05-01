import { CommonModule } from '@angular/common';
import { ChangeDetectorRef, Component, Input, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Basketball } from '../../services/basketball';
import { League } from '../../models/league';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-league-edit',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './league-edit.html',
  styleUrl: './league-edit.css',
})
export class LeagueEdit implements OnInit{
  @Input() id!: string;
  league?: League;

  constructor(private service: Basketball, private router: Router, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    console.log('Odebrane ID z URL:', this.id);
    if (this.id) {
      this.service.getOne(this.id).subscribe({
        next: (data) => {
          this.league = data;
          this.cdr.detectChanges();
        },
        error: (err) => console.error('Nie udało się pobrać ligi:', err)
      });
    }
  }

  update() {
    if (this.league) {
      this.service.update(this.id, this.league).subscribe(() => {
        this.router.navigate(['/']);
      });
    }
  }
}
