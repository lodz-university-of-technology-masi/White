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
import {NgbdModalContent, NgbdModalEditPosition, NgbdModalNewTest, TestTemplatesComponent} from './test-templates/test-templates.component';
import {TestTemplateService} from './services/test-template.service';
import { PositionsComponent } from './positions/positions.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {PositionsService} from './services/positions.service';
import {ToastrModule} from 'ng6-toastr-notifications';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MessageService} from './services/message.service';
import { TestResultComponent } from './test-result/test-result.component';
import {TestTemplateContentService} from './services/test-template-content.service';
import {TestResultService} from './services/test-result.service';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    TestTemplatesComponent,
    PositionsComponent,
    NgbdModalContent,
    NgbdModalEditPosition,
    NgbdModalNewTest,
    TestResultComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BsDropdownModule.forRoot(),
    CollapseModule.forRoot(),
    HttpClientModule,
    FormsModule,
    NgbModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot()
  ],
  providers: [
    Configuration,
    HttpErrorHandlerService,
    HttpService,
    TestTemplateService,
    PositionsService,
    TestTemplateContentService,
    TestResultService,
    MessageService
  ],
  bootstrap: [AppComponent],
  entryComponents: [NgbdModalContent, NgbdModalEditPosition, NgbdModalNewTest]
})
export class AppModule {
}
