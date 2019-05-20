import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ChoiceScaleQuestionComponent } from './choice-scale-question.component';

describe('ChoiceScaleQuestionComponent', () => {
  let component: ChoiceScaleQuestionComponent;
  let fixture: ComponentFixture<ChoiceScaleQuestionComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ChoiceScaleQuestionComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ChoiceScaleQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
