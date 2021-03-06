import {Injectable} from "@angular/core";
import {HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest, HttpResponse} from "@angular/common/http";
import {Observable, throwError} from "rxjs";
import {CacheService} from "../services/utils/cache.service";
import {catchError, map} from "rxjs/operators";
import {User} from "../models/user";
import {SnackbarService} from "../services/utils/snackbar.service";
import {HttpStatusCode} from "@angular/common/http";
import {Router} from "@angular/router";
import {LoadingService} from "../services/utils/loading.service";

const BASE_URL = 'http://localhost:8080/api/v1';

@Injectable()
export class TechnicalBusinessEssentialsInterceptor implements HttpInterceptor {
  constructor(private cacheService: CacheService, private snackbar: SnackbarService, private router: Router, private loadingService: LoadingService) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq = req.clone({
      body: req.body,
      params: req.params,
      headers: req.headers,
      url: TechnicalBusinessEssentialsInterceptor.appendBaseUrl(req.url)
    });

    try {
      const user = (this.cacheService.getData('user') as User);
      authReq = req.clone({
        setParams: {
          token: user.token?.token || ''
        },
        url: TechnicalBusinessEssentialsInterceptor.appendBaseUrl(req.url),
        body: req.body,
        headers: req.headers
      });
    } catch (ex) {
      // Logging in/Registering.
    }

    return next.handle(authReq)
      .pipe(
        map((resp: any) => {
          return resp;
        }),
        catchError(ex => {
          this.loadingService.stop();
          if (ex.status === HttpStatusCode.Unauthorized) {
            this.snackbar.show('Token expired, please log back in.')
            this.cacheService.clearData();
            this.router.navigate(['/login']);
          } else if (ex.status === HttpStatusCode.Forbidden) {
            this.snackbar.show('This content is forbidden. Please log in with admin privileges.')
            this.router.navigate(['/forbidden']);
          } else if (ex.status === HttpStatusCode.BadRequest) {
            return new Observable<HttpEvent<any>>(ex);
          } else {
            console.log(ex);
            this.snackbar.show('An unknown exception occurred. Please contact the admin.')
          }
          return new Observable<HttpEvent<any>>();
        })
      );
  }

  private static appendBaseUrl(url: string): string {
    return (BASE_URL + ((url.startsWith('/')) ? url : `/${url}`));
  }
}
