import {Component, HostListener} from '@angular/core';
import {Metric} from './metric';
import {MessageService} from './services/message.service';
import {MetricService} from './services/metric.service';
import {ViewportScroller} from '@angular/common';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'white-test-app-frontend';
  metricOn = false;
  metric: Metric;
  interval;
  private windowHeight: number;
  private windowWidth: number;
  private lastX: number;
  private lastY: number;

  constructor(private messageService: MessageService,
              private metricService: MetricService,
              private scroller: ViewportScroller) {
    this.windowHeight = window.innerHeight;
    this.windowWidth = window.innerWidth;
    this.lastX = 0;
    this.lastY = 0;
  }

  @HostListener('document:keyup', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.shiftKey) {
      if (event.key === 'D'.valueOf()) {
        this.metricOn = !this.metricOn;
        if (this.metricOn) {
          this.metric = new Metric();
          this.messageService.info('Metryki START');
          this.startMetrics();
        } else {
          this.stopMetrics();
          this.messageService.info('Metryki STOP');
          this.metricService.add(this.metric).subscribe(() => {
            this.messageService.success('Metryka zapisana pomyślnie');
          });
        }
      }
    }
  }

  startMetrics() {
    this.interval = setInterval(() => {
      this.metric.time++;
    }, 100);
  }

  stopMetrics() {
    clearInterval(this.interval);
    this.metric.time = this.metric.time * 100;
  }

  private getClickCount() {
    if (this.metricOn) {
      this.metric.mouseClicks++;
    }
  }

  @HostListener('click', ['$event'])
  onMouseLeft(event: any) {
    this.getClickCount();
    const [xOffset, yOffset] = this.scroller.getScrollPosition();

    const currentX = event.clientX + xOffset;
    const currentY = event.clientY + yOffset;

    this.metric.distance += this.calculateDistance(this.lastX, this.lastY, currentX, currentY);
    this.lastX = currentX;
    this.lastY = currentY;
  }

  @HostListener('contextmenu', ['$event'])
  onMouseRight(event: any) {
    this.getClickCount();
  }

  @HostListener('mouseup', ['$event'])
  onAuxClick(event) {
    if (event.which === 2 || event.button === 1)
      this.getClickCount();
  }

  private calculateDistance(x1: number, y1: number, x2: number, y2: number): number {
    return Math.ceil(Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2)));
  }

}
