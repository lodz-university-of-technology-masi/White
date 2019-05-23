import { Injectable } from '@angular/core';
import {Metric} from '../metric';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';

@Injectable({
  providedIn: 'root'
})
export class MetricService {

  private endpoint = this.config.ApiPath + '/metric';

  constructor(private httpService: HttpService,
              private config: Configuration) {
  }

  add(metric: Metric) {
    return this.httpService.post(this.endpoint, metric);
  }
}
