import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {SessionService} from '../services/session.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  isCollapsed = true;

  isUserLoggedIn = localStorage.getItem('token') !== null;
  roles = {ROLE_CANDIDATE: false, ROLE_REDACTOR: false, ROLE_MODERATOR: false};

  constructor(private sessionService: SessionService,
              private router: Router) {
  }

  ngOnInit() {
    this.sessionService.watchSession().subscribe(loggedIn => {
      if (loggedIn) {
        this.isUserLoggedIn = true;
        this.initRoles();
      } else {
        this.isUserLoggedIn = false;
        this.resetRoles();
      }
    });

    if (this.isUserLoggedIn) {
      this.initRoles();
    }
  }

  logout() {
    this.sessionService.resetSession();
    this.router.navigate(['/login']);
  }

  private initRoles() {
    Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = this.sessionService.hasUserRole(key));
  }

  private resetRoles() {
    Object.entries(this.roles).forEach(([key, value]) => this.roles[key] = false);
  }

}
