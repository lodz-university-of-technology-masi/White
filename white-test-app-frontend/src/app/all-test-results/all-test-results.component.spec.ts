import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {AllTestResultsComponent} from './all-test-results.component';

describe('AllTestResultsComponent', () => {
  let component: AllTestResultsComponent;
  let fixture: ComponentFixture<AllTestResultsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AllTestResultsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AllTestResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
