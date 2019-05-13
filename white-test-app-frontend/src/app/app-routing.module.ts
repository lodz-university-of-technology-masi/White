import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {TestTemplatesComponent} from './test-templates/test-templates.component';
import {PositionsComponent} from './positions/positions.component';
import {TestResultComponent} from './test-result/test-result.component';
import {TestCheckComponent} from './test-check/test-check.component';
import {AllTestResultsComponent} from './all-test-results/all-test-results.component';


const routes: Routes = [
  {path: '', component: AppComponent},
  {path: 'test-template', component: TestTemplatesComponent},
  {path: 'positions', component: PositionsComponent},
  {path: 'test/:id', component: TestResultComponent},
  {path: 'results', component: AllTestResultsComponent},
  {path: 'results/check/:id', component: TestCheckComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [ RouterModule ]
})
export class AppRoutingModule { }
