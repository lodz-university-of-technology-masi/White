import {Component, OnInit} from '@angular/core';
import {Redactor} from './model/redactor';
import {MessageService} from '../services/message.service';
import {AccountService} from '../services/account.service';


@Component({
  selector: 'app-redactors-management',
  templateUrl: './redactors-management.component.html',
  styleUrls: ['./redactors-management.component.css']
})
export class RedactorsManagementComponent implements OnInit {

  redactor: Redactor;
  redactors: Redactor[];

  constructor(private accountService: AccountService,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.redactor = new Redactor();
    this.loadRedactors();
  }

  private loadRedactors() {
    this.accountService.getAllRedactors().subscribe(t => {
        this.redactors = t;
        console.log(t);
      }
    );
  }

  onDeletedButtonClicked(username: string) {
    this.accountService.deleteRedactor(username).subscribe(
      success => {
        this.messageService.success('Sukces');
        this.loadRedactors();
      }, error => {
        this.messageService.error('Błąd');
      });
  }
}
