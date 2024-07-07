import {
  ActivatedRouteSnapshot,
  CanActivate,
  CanActivateFn,
  GuardResult,
  MaybeAsync, Router,
  RouterStateSnapshot
} from '@angular/router';
import {AuthService} from "../services/auth.service";
import {Injectable} from "@angular/core";

@Injectable({providedIn: 'root'})
export class AuthorizationGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {
  }

  canActivate(route: ActivatedRouteSnapshot,
              state: RouterStateSnapshot): MaybeAsync<GuardResult> {
    if(this.authService.isAuthenticated){
      let requiredROles = route.data['roles'];
      let userRoles = this.authService.roles;
      for(let role of userRoles){
        if(requiredROles.includes(role)){
          return true;
        }
      }
      return false;
    }

    else {
      this.router.navigateByUrl("/login")
      return false;
    }
    }
}
