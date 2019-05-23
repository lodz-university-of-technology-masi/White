import {BrowserModule} from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {NavbarComponent} from './navbar/navbar.component';
import {AppRoutingModule} from './app-routing.module';
import {BsDropdownModule, CollapseModule} from 'ngx-bootstrap';
import {HttpErrorHandlerService} from './services/http-error-handler.service';
import {HttpService} from './services/http-service.service';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {Configuration} from './configuration';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {
  NgbdModalContent,
  NgbdModalEditPosition,
  NgbdModalModifyTest,
  NgbdModalNewTest,
  TestTemplatesComponent
} from './test-templates/test-templates.component';
import {TestTemplateService} from './services/test-template.service';
import {PositionsComponent} from './positions/positions.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {PositionsService} from './services/positions.service';
import {ToastrModule} from 'ng6-toastr-notifications';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MessageService} from './services/message.service';
import {TestResultComponent} from './test-result/test-result.component';
import {TestTemplateContentService} from './services/test-template-content.service';
import {TestResultService} from './services/test-result.service';
import {TestCheckComponent} from './test-check/test-check.component';
import {AllTestResultsComponent} from './all-test-results/all-test-results.component';
import {UiSwitchModule} from 'ngx-ui-switch';
import {NgbdModalEditRedactor, RedactorsManagementComponent} from './redactors-management/redactors-management.component';
import {QuestionsComponent} from './questions/questions.component';
import {
  MatButtonModule,
  MatCardModule,
  MatFormFieldModule,
  MatInputModule,
  MatRadioModule,
  MatSelectModule,
  MatSnackBarModule,
  MatSliderModule,
  MatSlideToggleModule
} from '@angular/material';
import {QuestionComponent} from './questions/question/question.component';
import {ChoiceScaleQuestionComponent} from './questions/choice-scale-question/choice-scale-question.component';
import {LoginComponent} from './login/login.component';
import {SessionService} from './services/session.service';
import {AuthService} from './services/auth.service';
import {ErrorPageComponent} from './error-page/error-page.component';
import {GeneralRouteGuard} from './services/general-route-guard';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    TestTemplatesComponent,
    PositionsComponent,
    NgbdModalContent,
    NgbdModalEditPosition,
    NgbdModalEditRedactor,
    NgbdModalNewTest,
    NgbdModalModifyTest,
    TestResultComponent,
    TestCheckComponent,
    AllTestResultsComponent,
    RedactorsManagementComponent,
    AllTestResultsComponent,
    QuestionsComponent,
    QuestionComponent,
    ChoiceScaleQuestionComponent,
    RedactorsManagementComponent,
    LoginComponent,
    ErrorPageComponent
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
    ToastrModule.forRoot(),
    UiSwitchModule.forRoot({
      size: 'small',
      color: 'rgb(0, 189, 99)',
      switchColor: '#80FFA2',
      defaultBgColor: '#ff1850',
      defaultBoColor: '#101563',
      checkedLabel: 'dobrze',
      uncheckedLabel: 'źle'
    }),
    MatInputModule,
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    MatRadioModule,
    ReactiveFormsModule,
    MatSelectModule,
    MatSnackBarModule,
    MatSliderModule,
    MatSlideToggleModule
  ],
  providers: [
    Configuration,
    HttpErrorHandlerService,
    HttpService,
    TestTemplateService,
    PositionsService,
    TestTemplateContentService,
    TestResultService,
    MessageService,
    AuthService,
    SessionService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: HttpErrorHandlerService,
      multi: true
    },
    GeneralRouteGuard
  ],
  bootstrap: [AppComponent],
  entryComponents: [NgbdModalContent, NgbdModalEditPosition, NgbdModalNewTest, NgbdModalEditRedactor, NgbdModalModifyTest],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class AppModule {
}
