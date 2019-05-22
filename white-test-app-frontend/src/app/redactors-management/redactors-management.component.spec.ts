import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RedactorsManagementComponent } from './redactors-management.component';

describe('RedactorsManagementComponent', () => {
  let component: RedactorsManagementComponent;
  let fixture: ComponentFixture<RedactorsManagementComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RedactorsManagementComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RedactorsManagementComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
