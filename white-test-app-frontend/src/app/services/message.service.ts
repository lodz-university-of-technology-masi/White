import {Injectable} from '@angular/core';
import {ToastrManager} from 'ng6-toastr-notifications';

@Injectable()
export class MessageService {

  constructor(public toastrService: ToastrManager) {
  }

  success(message: string, title?: string) {
    this.toastrService.successToastr(message, title);
  }

  info(message: string, title?: string) {
    this.toastrService.infoToastr(message, title);
  }

  warning(message: string, title?: string) {
    this.toastrService.warningToastr(message, title);
  }

  error(message: string, title?: string) {
    this.toastrService.errorToastr(message, title);
  }
}
