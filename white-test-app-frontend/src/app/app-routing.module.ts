import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {TestTemplatesComponent} from './test-templates/test-templates.component';
import {PositionsComponent} from './positions/positions.component';
import {TestResultComponent} from './test-result/test-result.component';
import {TestCheckComponent} from './test-check/test-check.component';
import {AllTestResultsComponent} from './all-test-results/all-test-results.component';
import {RedactorsManagementComponent} from './redactors-management/redactors-management.component';
import {LoginComponent} from './login/login.component';
import {ErrorPageComponent} from './error-page/error-page.component';
import {GeneralRouteGuard} from './services/general-route-guard';


const routes: Routes = [
  {path: '', component: AppComponent},
  {path: 'test-template', component: TestTemplatesComponent, canActivate: [GeneralRouteGuard]},
  {path: 'positions', component: PositionsComponent, canActivate: [GeneralRouteGuard]},
  {path: 'test/:id', component: TestResultComponent, canActivate: [GeneralRouteGuard]},
  {path: 'results', component: AllTestResultsComponent, canActivate: [GeneralRouteGuard]},
  {path: 'results/check/:id', component: TestCheckComponent, canActivate: [GeneralRouteGuard]},
  {path: 'redactors', component: RedactorsManagementComponent, canActivate: [GeneralRouteGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'error404', component: ErrorPageComponent}

];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
