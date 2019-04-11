import {Component, OnInit} from '@angular/core';
import {TestTemplate} from './model/test-template';
import {TestTemplateService} from '../services/test-template.service';

@Component({
  selector: 'app-test-templates',
  templateUrl: './test-templates.component.html',
  styleUrls: ['./test-templates.component.css']
})
export class TestTemplatesComponent implements OnInit {

  testTemplates: TestTemplate[];

  constructor(private testTemplateService: TestTemplateService) {
  }

  ngOnInit() {
    this.loadTemplates();
  }

  loadTemplates() {
    this.testTemplateService.getAll().subscribe( t => {this.testTemplates = t; console.log(t); } );
  }

}
