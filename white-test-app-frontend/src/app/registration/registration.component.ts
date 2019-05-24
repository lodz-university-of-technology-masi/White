import {Component, OnInit} from '@angular/core';
import {SessionService} from "../services/session.service";
import {Router} from "@angular/router";
import {MessageService} from "../services/message.service";
import {AuthService} from "../services/auth.service";
import {User} from "./model/user";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  user: User;

  constructor(private authService: AuthService,
              private sessionService: SessionService,
              private messageService: MessageService,
              private router: Router) {
  }

  ngOnInit() {
    this.user = new User();
  }

  register() {
    this.authService.register(this.user)
      .subscribe(
        data => {
          this.messageService.success('Rejestracja przebiegła pomyślnie!');
          this.router.navigate(['/login']);
        },
        error => {
          this.messageService.error('Podana nazwa użytkownika lub email już istnieje!');
        });
  }
}
