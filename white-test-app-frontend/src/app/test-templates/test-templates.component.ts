import {Component, OnInit} from '@angular/core';
import {TestTemplate} from './model/test-template';
import {TestTemplateService} from '../services/test-template.service';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {NewTemplate} from './model/new-template';
import {PositionsService} from '../services/positions.service';

@Component({
  selector: 'app-test-templates',
  templateUrl: './test-templates.component.html',
  styleUrls: ['./test-templates.component.css']
})
export class TestTemplatesComponent implements OnInit {

  testTemplates: TestTemplate[];
  newTemplate: NewTemplate;
  positions: string[];

  constructor(private testTemplateService: TestTemplateService,
              private positionService: PositionsService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.loadTemplates();
  }

  loadTemplates() {
    this.testTemplateService.getAll().subscribe( t => {this.testTemplates = t; console.log(t); } );
  }

  addNew() {
    this.testTemplateService.add(this.newTemplate).subscribe(s => {
    }, e => {
    });
  }

  open(content) {
    this.positionService.getAllPositions().subscribe(positions => this.positions = positions.map(p => p.name));
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
  }

}
