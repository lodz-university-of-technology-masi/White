import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {AppRoutingModule} from './app-routing.module';
import {BsDropdownModule, CollapseModule, ModalModule} from 'ngx-bootstrap';
import {HttpErrorHandlerService} from './services/http-error-handler.service';
import {HttpService} from './services/http-service.service';
import {HttpClientModule} from '@angular/common/http';
import {Configuration} from './configuration';
import {FormsModule} from '@angular/forms';
import { TestTemplatesComponent } from './test-templates/test-templates.component';
import {TestTemplateService} from './services/test-template.service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    TestTemplatesComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    CollapseModule.forRoot(),
    ModalModule.forRoot(),
    HttpClientModule,
    FormsModule
  ],
  providers: [
    Configuration,
    HttpErrorHandlerService,
    HttpService,
    TestTemplateService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}