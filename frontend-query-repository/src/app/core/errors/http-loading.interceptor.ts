import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {finalize, Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {LoadingDialogService} from "../../shared/loading/loading-dialog.service";

@Injectable()
export class HttpLoadingInterceptor implements HttpInterceptor {
  constructor(private loadingDialogService: LoadingDialogService) {}

  intercept(
    request: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {
    this.loadingDialogService.openDialog();
    return next.handle(request).pipe(
      finalize(() => {
        this.loadingDialogService.hideDialog();
      })
    ) as Observable<HttpEvent<any>>;
  }
}
