import {Component, Input, OnInit} from '@angular/core';
import {TestTemplate} from './model/test-template';
import {TestTemplateService} from '../services/test-template.service';
import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {NewTemplate} from './model/new-template';
import {PositionsService} from '../services/positions.service';
import {Position} from '../positions/model/position';
import {MessageService} from '../services/message.service';

@Component({
  selector: 'ngbd-modal-edit-position',
  templateUrl: './edit-position-in-test.html'
})
export class NgbdModalEditPosition implements OnInit {
  @Input() test;
  testTemplates: TestTemplate[];
  positions: Position[];
  selectedPosition: string;

  constructor(public activeModal: NgbActiveModal,
              private testTemplateService: TestTemplateService,
              private modalService: NgbModal,
              private positionsService: PositionsService) {

  }

  ngOnInit(): void {
    this.getPositions();
  }

  private getPositions() {
    this.positionsService.getAllPositions().subscribe(t => {
      this.positions = t;
      console.log(t);
    });
  }

  assignPositionToTest(id: number) {
    const name = this.selectedPosition.replace('/\s/g', '+');
    this.testTemplateService.assignPositionToTest(id, name).subscribe(success => {
    }, error => {
    });
  }
}

@Component({
  selector: 'ngbd-modal-content',
  templateUrl: './edit-test-modal.html'
})
export class NgbdModalContent {
  @Input() test;

  open(test) {
    const modal: NgbModalRef = this.modalService.open(NgbdModalEditPosition, {ariaLabelledBy: 'modal-basic-title'});
    modal.componentInstance.test = test;
    modal.result.then((result) => {
      console.log(result);
    }, (reason) => {
      console.log(reason);
    });
  }

  constructor(public activeModal: NgbActiveModal,
              private modalService: NgbModal) {

  }
}

@Component({
  selector: 'ngbd-modal-new-modal',
  templateUrl: './new-test-modal.html'
})
export class NgbdModalNewTest implements OnInit {
  positions: string[];
  newTemplate: NewTemplate;

  constructor(public activeModal: NgbActiveModal,
              private testTemplateService: TestTemplateService,
              private messageService: MessageService,
              private modalService: NgbModal,
              private positionsService: PositionsService) {

  }

  ngOnInit(): void {
    this.newTemplate = new NewTemplate();
    this.getPositions();
  }

  private getPositions() {
    this.positionsService.getAllPositions().subscribe(positions => this.positions = positions.map(p => p.name));
  }

  addNew() {
    this.testTemplateService.add(this.newTemplate).subscribe(s => {
      this.messageService.success('Sukces');
    }, e => {
      this.messageService.error('Błąd');
    });
  }

}

@Component({
  selector: 'app-test-templates',
  templateUrl: './test-templates.component.html',
  styleUrls: ['./test-templates.component.css']
})
export class TestTemplatesComponent implements OnInit {

  testTemplates: TestTemplate[];
  positions: Position[];
  ngModalNewTest = NgbdModalNewTest;

  constructor(private testTemplateService: TestTemplateService,
              private positionService: PositionsService,
              private modalService: NgbModal,
              private messageService: MessageService) {
  }

  ngOnInit() {
    this.loadTemplates();
  }

  loadTemplates() {
    this.testTemplateService.getAll().subscribe(t => {
      this.testTemplates = t;
      console.log(t);
    });
  }

  open(content, test) {
    const modal: NgbModalRef = this.modalService.open(NgbdModalContent, {ariaLabelledBy: 'modal-basic-title'});
    modal.componentInstance.test = test;
    modal.result.then((result) => {
      this.loadTemplates();
    }, (reason) => {
      this.loadTemplates();
    });
  }

  translate(templateId: number, currentLang: string) {
    this.testTemplateService.translate(templateId, currentLang).subscribe(s => {
        this.messageService.success('Sukces');
        this.loadTemplates();
      },
      e => this.messageService.error('Błąd'));
  }

  openModal(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
      this.loadTemplates();
    }, (reason) => {
      this.loadTemplates();
    });
  }

}
