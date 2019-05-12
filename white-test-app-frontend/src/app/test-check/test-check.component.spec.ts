import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { TestCheckComponent } from './test-check.component';

describe('TestCheckComponent', () => {
  let component: TestCheckComponent;
  let fixture: ComponentFixture<TestCheckComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ TestCheckComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(TestCheckComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
