import { Injectable } from '@angular/core';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {Position} from '../positions/model/position';
import {Observable} from "rxjs/index";

@Injectable({
  providedIn: 'root'
})
export class PositionsService {

  private endpoint = this.config.ApiPath + '/position';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  addNew(position: Position) {
    return this.httpService.post(this.endpoint, position);
  }

  getAllPositions(): Observable<Position[]> {
    return this.httpService.get<Position[]>(this.endpoint);
  }
}
