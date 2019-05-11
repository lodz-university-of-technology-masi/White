import { TestBed } from '@angular/core/testing';

import { TestTemplateContentService } from './test-template-content.service';

describe('TestTemplateContentService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TestTemplateContentService = TestBed.get(TestTemplateContentService);
    expect(service).toBeTruthy();
  });
});
