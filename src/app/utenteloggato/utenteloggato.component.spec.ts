import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UtenteloggatoComponent } from './utenteloggato.component';

describe('UtenteloggatoComponent', () => {
  let component: UtenteloggatoComponent;
  let fixture: ComponentFixture<UtenteloggatoComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UtenteloggatoComponent]
    });
    fixture = TestBed.createComponent(UtenteloggatoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
