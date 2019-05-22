import {Component, OnInit} from '@angular/core';
import {AuthData} from './model/auth-data';
import {Router} from '@angular/router';
import {MessageService} from '../services/message.service';
import {SessionService} from '../services/session.service';
import {AuthService} from '../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  user: AuthData;

  constructor(private authService: AuthService,
              private sessionService: SessionService,
              private messageService: MessageService,
              private router: Router) {
  }

  ngOnInit() {
    this.user = new AuthData();
  }

  login() {
    this.authService.login(this.user).subscribe(
      success => {
        this.sessionService.setUser(success);
        this.router.navigateByUrl('/');
      }, error => {
        this.messageService.error('Błędny login lub hasło');
        console.log(error);
      });
  }

}
