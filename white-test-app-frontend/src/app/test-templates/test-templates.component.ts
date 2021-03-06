import {Component, HostListener, Input, OnInit} from '@angular/core';
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
import {SessionService} from '../services/session.service';
import {MatSnackBar} from '@angular/material';
import {MetricService} from "../services/metric.service";
import {ViewportScroller} from "@angular/common";

export const WIKI_URL = 'https://en.wikipedia.org/wiki/';
export const WIKI_URL_PL = 'https://pl.wikipedia.org/wiki/';
export const SYNONYMS_URL = 'https://www.thesaurus.com/browse/';
export const SYNONYMS_URL_PL = 'https://www.synonimy.pl/synonim/';

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
              private positionsService: PositionsService,
              private metricService: MetricService,
              private messageService: MessageService,
              private scroller: ViewportScroller) {

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

  @HostListener('click', ['$event'])
  onMouseLeft(event: any) {
    this.metricService.getClickCount();
    if (this.metricService.metricOn) {
      const [xOffset, yOffset] = this.scroller.getScrollPosition();

      const currentX = event.clientX + xOffset;
      const currentY = event.clientY + yOffset;

      this.metricService.metric.distance += this.metricService.calculateDistance(this.metricService.lastX, this.metricService.lastY, currentX, currentY);
      this.metricService.lastX = currentX;
      this.metricService.lastY = currentY;
    }
  }

  @HostListener('contextmenu', ['$event'])
  onMouseRight(event: any) {
    this.metricService.getClickCount();
  }

  @HostListener('mouseup', ['$event'])
  onAuxClick(event) {
    if (event.which === 2 || event.button === 1) {
      this.metricService.getClickCount();
    }
  }

  @HostListener('window:resize', ['$event'])
  onChangeWindowsSize(event: any) {
    this.metricService.metric.resW = event.target.innerWidth;
    this.metricService.metric.resH = event.target.innerHeight;
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

  isActive(role: string): boolean {
    return this.sessionService.hasUserRole(role);
  }

  constructor(public activeModal: NgbActiveModal,
              private modalService: NgbModal,
              private testService: TestTemplateContentService,
              private messageService: MessageService,
              private sessionService: SessionService,
              private metricService: MetricService,
              private scroller: ViewportScroller) {

  }

  @HostListener('click', ['$event'])
  onMouseLeft(event: any) {
    this.metricService.getClickCount();
    if (this.metricService.metricOn) {
      const [xOffset, yOffset] = this.scroller.getScrollPosition();

      const currentX = event.clientX + xOffset;
      const currentY = event.clientY + yOffset;

      this.metricService.metric.distance += this.metricService.calculateDistance(
        this.metricService.lastX, this.metricService.lastY, currentX, currentY
      );
      this.metricService.lastX = currentX;
      this.metricService.lastY = currentY;
    }
  }

  @HostListener('contextmenu', ['$event'])
  onMouseRight(event: any) {
    this.metricService.getClickCount();
  }

  @HostListener('mouseup', ['$event'])
  onAuxClick(event) {
    if (event.which === 2 || event.button === 1) {
      this.metricService.getClickCount();
    }
  }

  @HostListener('window:resize', ['$event'])
  onChangeWindowsSize(event: any) {
    this.metricService.metric.resW = event.target.innerWidth;
    this.metricService.metric.resH = event.target.innerHeight;
  }
}

@Component({
  selector: 'ngbd-modal-new-modal',
  templateUrl: './new-test-modal.html'
})
export class NgbdModalNewTest implements OnInit {
  @Input() test;
  positions: string[];
  newTemplate: NewTemplate;
  questions: Question[];
  selectedText: string;

  constructor(public activeModal: NgbActiveModal,
              private testTemplateService: TestTemplateService,
              private messageService: MessageService,
              private modalService: NgbModal,
              private positionsService: PositionsService,
              private questionService: QuestionService,
              private snackBar: MatSnackBar,
              private metricService: MetricService,
              private scroller: ViewportScroller) {
  }

  ngOnInit(): void {
    this.newTemplate = new NewTemplate();
    this.newTemplate.questions = [];
    this.selectedText = '';
    this.getPositions();
    this.setDataWhenNewLangVersion();
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

  getSelectedText() {
    let text = '';
    if (window.getSelection().toString()) {
      text = window.getSelection().toString();
    }
    this.selectedText = text;
  }

  openWikipedia(event) {
    if (this.newTemplate.lang === 'PL') {
      window.open(WIKI_URL_PL + this.selectedText, '_blank');
    } else {
      window.open(WIKI_URL + this.selectedText, '_blank');
    }
  }

  findSynonyms(event) {
    if (this.newTemplate.lang === 'PL') {
      window.open(SYNONYMS_URL_PL + this.selectedText, '_blank');
    } else {
      window.open(SYNONYMS_URL + this.selectedText, '_blank');
    }
  }

  @HostListener('mouseup', ['$event']) mouseClick() {
    if (this.selectedText) {
      this.openSnackBar();
    }
  }

  openSnackBar() {
    this.snackBar.open('SHIFT + ↑  -> Szukaj na Wikipedii | SHIFT + ↓  -> Szukaj synonimu', 'zamknij', {
      duration: 4000
    });
  }

  private setDataWhenNewLangVersion() {
    if (this.test != null) {
      this.newTemplate.testName = this.test.name;
      if (this.newTemplate.lang === 'PL') {
        this.newTemplate.lang = 'EN';
      } else {
        this.newTemplate.lang = 'PL';
      }
      this.newTemplate.position = this.test.position;
      this.newTemplate.id = this.test.testTemplateId;
    }
  }

  addNewLangVersion() {
    this.testTemplateService.addNewLanguageVersion(this.newTemplate).subscribe(s => {
      this.messageService.success('Sukces');
    }, e => {
      this.messageService.error('Błąd');
    });
  }

  @HostListener('click', ['$event'])
  onMouseLeft(event: any) {
    this.metricService.getClickCount();
    if (this.metricService.metricOn) {
      const [xOffset, yOffset] = this.scroller.getScrollPosition();

      const currentX = event.clientX + xOffset;
      const currentY = event.clientY + yOffset;

      this.metricService.metric.distance += this.metricService.calculateDistance(
        this.metricService.lastX, this.metricService.lastY, currentX, currentY
      );
      this.metricService.lastX = currentX;
      this.metricService.lastY = currentY;
    }
  }

  @HostListener('contextmenu', ['$event'])
  onMouseRight(event: any) {
    this.metricService.getClickCount();
  }

  @HostListener('mouseup', ['$event'])
  onAuxClick(event) {
    if (event.which === 2 || event.button === 1) {
      this.metricService.getClickCount();
    }
  }

  @HostListener('window:resize', ['$event'])
  onChangeWindowsSize(event: any) {
    this.metricService.metric.resW = event.target.innerWidth;
    this.metricService.metric.resH = event.target.innerHeight;
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
  selectedText: string;

  constructor(public activeModal: NgbActiveModal,
              private testTemplateContentService: TestTemplateContentService,
              private messageService: MessageService,
              private modalService: NgbModal,
              private positionsService: PositionsService,
              private snackBar: MatSnackBar,
              private metricService: MetricService,
              private scroller: ViewportScroller) {
  }

  ngOnInit(): void {
    this.loadTestTemplate();
    this.getPositions();
    this.selectedText = '';
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

  getSelectedText() {
    let text = "";
    if (window.getSelection().toString()) {
      text = window.getSelection().toString();
    }
    this.selectedText = text;
  }

  openWikipedia(event) {
    if (this.test.lang === 'PL') {
      window.open(WIKI_URL_PL + this.selectedText, '_blank');
    } else {
      window.open(WIKI_URL + this.selectedText, '_blank');
    }
  }

  findSynonyms(event) {
    if (this.test.lang === 'PL') {
      window.open(SYNONYMS_URL_PL + this.selectedText, '_blank');
    } else {
      window.open(SYNONYMS_URL + this.selectedText, '_blank');
    }
  }

  @HostListener('mouseup', ['$event']) mouseClick() {
    if (this.selectedText) {
      this.openSnackBar();
    }
  }

  openSnackBar() {
    this.snackBar.open('SHIFT + ↑  -> Szukaj na Wikipedii | SHIFT + ↓  -> Szukaj synonimu', 'zamknij', {
      duration: 4000
    });
  }

  @HostListener('click', ['$event'])
  onMouseLeft(event: any) {
    this.metricService.getClickCount();
    if (this.metricService.metricOn) {
      const [xOffset, yOffset] = this.scroller.getScrollPosition();

      const currentX = event.clientX + xOffset;
      const currentY = event.clientY + yOffset;

      this.metricService.metric.distance += this.metricService.calculateDistance(
        this.metricService.lastX, this.metricService.lastY, currentX, currentY
      );
      this.metricService.lastX = currentX;
      this.metricService.lastY = currentY;
    }
  }

  @HostListener('contextmenu', ['$event'])
  onMouseRight(event: any) {
    this.metricService.getClickCount();
  }

  @HostListener('mouseup', ['$event'])
  onAuxClick(event) {
    if (event.which === 2 || event.button === 1) {
      this.metricService.getClickCount();
    }
  }

  @HostListener('window:resize', ['$event'])
  onChangeWindowsSize(event: any) {
    this.metricService.metric.resW = event.target.innerWidth;
    this.metricService.metric.resH = event.target.innerHeight;
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
              private messageService: MessageService,
              private sessionService: SessionService) {
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

  createNewLangVersion(test, content) {
    const modal: NgbModalRef = this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'});
    modal.componentInstance.test = test;
    modal.result.then((result) => {
      this.loadTemplates();
    }, (reason) => {
      this.loadTemplates();
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

  isActive(role: string): boolean {
    return this.sessionService.hasUserRole(role);
  }
}
