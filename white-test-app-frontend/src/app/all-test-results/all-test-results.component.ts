import {Component, OnInit} from '@angular/core';
import {TestResult} from './model/test-result';
import {TestResultService} from '../services/test-result.service';

@Component({
  selector: 'app-all-test-results',
  templateUrl: './all-test-results.component.html',
  styleUrls: ['./all-test-results.component.css']
})
export class AllTestResultsComponent implements OnInit {
  testResults: TestResult[] = [];

  constructor(private testResultService: TestResultService) {
  }

  ngOnInit() {
    this.loadTestResults();
  }

  loadTestResults() {
    this.testResultService.getAll().subscribe(testResults => this.testResults = testResults);
  }

}
