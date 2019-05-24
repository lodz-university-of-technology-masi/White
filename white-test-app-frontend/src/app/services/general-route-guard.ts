import {Injectable} from '@angular/core';
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from '@angular/router';
import {UrlAvailabilityForUserRoles} from './url-availability-for-user-roles';
import {SessionService} from './session.service';

@Injectable()
export class GeneralRouteGuard implements CanActivate {

  constructor(private sessionContextService: SessionService,
              private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    let available = false;
    const user = this.sessionContextService.getUser();

    if (user) {
        if (UrlAvailabilityForUserRoles[user.authorities[0].authority].includes(
          state.url.substring(0, state.url.indexOf('/', 1) > 1 ? state.url.indexOf('/', 1) : state.url.length))) {
          available = true;
      }

      if (!available) {
        this.router.navigateByUrl('/error404');
      }
      return available;
    } else {
      this.sessionContextService.resetSession();
      this.router.navigateByUrl('/login');
      return false;
    }
  }
}
