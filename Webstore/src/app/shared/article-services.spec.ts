import { TestBed } from '@angular/core/testing';

import { ArticleServices } from './article-services';

describe('ArticleServices', () => {
  let service: ArticleServices;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ArticleServices);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
