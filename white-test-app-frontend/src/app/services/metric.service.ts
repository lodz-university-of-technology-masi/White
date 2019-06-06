import {Injectable} from '@angular/core';
import {Metric} from '../metric';
import {HttpService} from './http-service.service';
import {Configuration} from '../configuration';
import {MessageService} from "./message.service";
import {DeviceDetectorService} from "ngx-device-detector";
import {ViewportScroller} from "@angular/common";
import html2canvas from 'html2canvas';

@Injectable({
  providedIn: 'root'
})
export class MetricService {

  private endpoint = this.config.ApiPath + '/metric';
  metricOn = false;
  metric: Metric;
  interval;
  windowHeight: number;
  windowWidth: number;
  lastX: number;
  lastY: number;

  constructor(private httpService: HttpService,
              private config: Configuration,
              private messageService: MessageService,
              private metricService: MetricService,
              private scroller: ViewportScroller,
              private deviceDetectorService: DeviceDetectorService) {
    this.windowHeight = window.innerHeight;
    this.windowWidth = window.innerWidth;
    this.lastX = 0;
    this.lastY = 0;
  }

  add(metric: Metric) {
    return this.httpService.post(this.endpoint, metric);
  }

  saveScreenshot(imgData: string) {
    return this.httpService.post(this.endpoint + '/screenshot', imgData);
  }

  startMetrics() {
    this.interval = setInterval(() => {
      this.metric.time++;
    }, 100);
  }

  stopMetrics() {
    clearInterval(this.interval);
    this.metric.time = this.metric.time * 100;
    this.metric.resW = window.innerWidth;
    this.metric.resH = window.innerHeight;
    this.metric.browser = this.deviceDetectorService.browser.charAt(0);
  }

  getClickCount() {
    if (this.metricOn) {
      this.metric.mouseClicks++;
    }
  }

  calculateDistance(x1: number, y1: number, x2: number, y2: number): number {
    return Math.ceil(Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
  }

  doScreenshot() {
    html2canvas(document.body).then(canvas => {
      const imgData = canvas.toDataURL('image/png');
      this.saveScreenshot(imgData).subscribe();
    });
  }
}
