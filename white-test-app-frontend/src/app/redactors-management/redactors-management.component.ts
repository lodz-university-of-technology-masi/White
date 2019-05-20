import {Component, OnInit} from '@angular/core';
import {Redactor} from './model/redactor';
import {MessageService} from '../services/message.service';
import {AccountService} from '../services/account.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-redactors-management',
  templateUrl: './redactors-management.component.html',
  styleUrls: ['./redactors-management.component.css']
})
export class RedactorsManagementComponent implements OnInit {

  redactor: Redactor;
  redactors: Redactor[];

  constructor(private accountService: AccountService,
              private modalService: NgbModal,
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

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
  }

  addNew() {
    this.accountService.addRedactor(this.redactor).subscribe(success => {
      this.messageService.success('Sukces');
      this.loadRedactors();
    }, error => {
      this.messageService.error('Błąd');
    });
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
