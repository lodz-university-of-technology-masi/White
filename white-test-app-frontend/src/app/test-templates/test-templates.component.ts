import {Component, Input, OnInit} from '@angular/core';
import {TestTemplate} from './model/test-template';
import {TestTemplateService} from '../services/test-template.service';
import {NgbActiveModal, NgbModal, NgbModalRef} from '@ng-bootstrap/ng-bootstrap';
import {PositionsService} from '../services/positions.service';
import {Position} from '../positions/model/position';

@Component({
  selector: 'ngbd-modal-edit-position',
  template: `
    <div class="modal-header">
      <h4 class="modal-title" id="modal-position-title">Edycja stanowiska w teście</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <form>
        <div class="form-group" *ngIf="positions">
          <select [(ngModel)]="selectedPosition" name="first">
            <option *ngFor="let position of positions">
              {{position.name}}
            </option>
          </select>
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark"
              (click)="activeModal.close('Save click'); assignPositionToTest(test.testTemplateId) ">
        Zapisz
      </button>
    </div>
  `
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
  template: `
    <div class="modal-header">
      <h4 class="modal-title" id="modal-basic-title">Edycja testu</h4>
      <button type="button" class="close" aria-label="Close" (click)="activeModal.dismiss('Cross click')">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="modal-body">
      <form>
        <div class="form-group">
          <button type="button" class="btn btn-primary mb-2" (click)="open(test)">Zmień stanowisko</button>
        </div>
      </form>
    </div>
    <div class="modal-footer">
      <button type="button" class="btn btn-outline-dark" (click)="activeModal.close('Save click');">Zapisz</button>
    </div>

  `
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
  selector: 'app-test-templates',
  templateUrl: './test-templates.component.html',
  styleUrls: ['./test-templates.component.css']
})
export class TestTemplatesComponent implements OnInit {

  testTemplates: TestTemplate[];
  positions: Position[];

  constructor(private testTemplateService: TestTemplateService,
              private modalService: NgbModal) {
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

}
