import {Component, Input, OnInit} from '@angular/core';
import {Redactor} from './model/redactor';
import {MessageService} from '../services/message.service';
import {AccountService} from '../services/account.service';
import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'ngbd-modal-edit-redactor',
  templateUrl: './edit-redactor-modal.html'
})
export class NgbdModalEditRedactor implements OnInit {

  @Input() redactorFromList;
  redactor: Redactor;

  ngOnInit() {
    this.redactor = new Redactor();
    this.redactor.username = this.redactorFromList.username;
    this.redactor.email = this.redactorFromList.email;
    this.redactor.password = this.redactorFromList.password;
    this.redactor.lang = this.redactorFromList.lang;
    this.redactor.role = this.redactorFromList.role;
  }

  updateRedactor() {
    if (this.redactor.password == null) {
      this.redactor.password = '';
    }

    this.accountService.updateRedactor(this.redactor).subscribe(success => {
      this.messageService.success('Sukces');
    }, error => {
      this.messageService.error('Błąd');
    });
  }


  constructor(public activeModal: NgbActiveModal,
              private modalService: NgbModal,
              private accountService: AccountService,
              private messageService: MessageService) {
  }
}


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

  onEditButtonClicked(redactor) {
    const modal: NgbModalRef = this.modalService.open(NgbdModalEditRedactor, {ariaLabelledBy: 'modal-edit-redactor'});
    modal.componentInstance.redactorFromList = redactor;
    modal.result.then((result) => {
      this.loadRedactors();
    }, (reason) => {
      this.loadRedactors();
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
