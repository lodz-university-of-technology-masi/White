import {Component, Input, OnInit} from '@angular/core';
import {TestTemplate} from './model/test-template';
import {TestTemplateService} from '../services/test-template.service';
import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {NewTemplate} from './model/new-template';
import {PositionsService} from '../services/positions.service';
import {Position} from '../positions/model/position';
import {MessageService} from '../services/message.service';
import {TestTemplateContentService} from '../services/test-template-content.service';
import {saveAs} from 'file-saver';
import {QuestionService} from '../services/question.service';
import {Question} from './model/question';
import {TestTemplateDetail} from './model/test-template-detail';

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
  templateUrl: './edit-test-modal.html',
  styleUrls: ['./edit-test-modal.css']
})
export class NgbdModalContent {
  @Input() test;
  fileToUpload: File = null;

  open(test) {
    const modal: NgbModalRef = this.modalService.open(NgbdModalEditPosition, {ariaLabelledBy: 'modal-basic-title'});
    modal.componentInstance.test = test;
    modal.result.then((result) => {
      console.log(result);
    }, (reason) => {
      console.log(reason);
    });
  }

  openToModify(test) {
    const modal: NgbModalRef = this.modalService.open(NgbdModalModifyTest, {ariaLabelledBy: 'modal-basic-title'});
    modal.componentInstance.test = test;
    modal.result.then((result) => {
      console.log(result);
    }, (reason) => {
      console.log(reason);
    });
  }

  exportPDF(test) {
    this.testService.generatePDF(test.id, test).subscribe((response) => {
      this.messageService.success('Sukces');
      const filename = response.headers.get('filename');
      const blob = new Blob([response.body], {type: 'pdf'});
      const url = URL.createObjectURL(blob);
      saveAs(blob, filename);
      window.open(url);
    }, e => {
      this.messageService.error('Błąd');
    });
  }

  importCsv(files) {
    this.fileToUpload = files.item(0);
    this.testService.importCsv(this.test.testTemplateId, this.fileToUpload).subscribe((response) => {
      this.messageService.success('Sukces');
    }, e => {
      this.messageService.error(e.error.message);
    });
  }

  exportCSV(test) {
    this.testService.exportCSV(test.id, test).subscribe((response) => {
      this.messageService.success('Sukces');
      const filename = response.headers.get('filename');
      const blob = new Blob([response.body], {type: 'text/csv'});
      saveAs(blob, filename);
    }, e => {
      this.messageService.error('Błąd');
    });
  }

  constructor(public activeModal: NgbActiveModal,
              private modalService: NgbModal,
              private testService: TestTemplateContentService,
              private messageService: MessageService) {

  }
}

@Component({
  selector: 'ngbd-modal-new-modal',
  templateUrl: './new-test-modal.html'
})
export class NgbdModalNewTest implements OnInit {
  positions: string[];
  newTemplate: NewTemplate;
  questions: Question[];

  constructor(public activeModal: NgbActiveModal,
              private testTemplateService: TestTemplateService,
              private messageService: MessageService,
              private modalService: NgbModal,
              private positionsService: PositionsService,
              private questionService: QuestionService) {

  }

  ngOnInit(): void {
    this.newTemplate = new NewTemplate();
    this.newTemplate.questions = [];
    this.getPositions();
  }

  private getPositions() {
    this.positionsService.getAllPositions().subscribe(positions => this.positions = positions.map(p => p.name));
  }

  addNew() {
    console.log(this.newTemplate);
    console.log(this.newTemplate.questions);
    this.testTemplateService.add(this.newTemplate).subscribe(s => {
      this.messageService.success('Sukces');
    }, e => {
      this.messageService.error('Błąd');
    });
  }
}


@Component({
  selector: 'ngbd-modal-modify-modal',
  templateUrl: './modify-test-modal.html'
})
export class NgbdModalModifyTest implements OnInit {
  @Input() test;
  positions: string[];
  testTemplate: TestTemplateDetail;

  constructor(public activeModal: NgbActiveModal,
              private testTemplateContentService: TestTemplateContentService,
              private messageService: MessageService,
              private modalService: NgbModal,
              private positionsService: PositionsService) {
  }

  ngOnInit(): void {
    this.loadTestTemplate();
    this.getPositions();
  }

  private loadTestTemplate() {
    this.testTemplateContentService.get(this.test.id).subscribe(test => {
      this.testTemplate = test;
      console.log(this.testTemplate);
    });
  }

  private getPositions() {
    this.positionsService.getAllPositions().subscribe(positions => this.positions = positions.map(p => p.name));
  }

  save() {
    this.testTemplateContentService.edit(this.testTemplate).subscribe(s => {
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

  deleteTest(id: number, currentLang: string) {
    this.testTemplateService.deleteTest(id, currentLang).subscribe(d => {
        this.messageService.success('Sukces');
        this.loadTemplates();
      },
      e => this.messageService.error('Błąd'));
  }
}
