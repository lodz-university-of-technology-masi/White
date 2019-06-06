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

  constructor(private messageService: MessageService,
              private metricService: MetricService,
              private scroller: ViewportScroller) {
  }

  @HostListener('document:keyup', ['$event'])
  handleKeyboardEvent(event: KeyboardEvent) {
    if (event.shiftKey) {
      if (event.key === 'D'.valueOf()) {
        this.metricService.metricOn = !this.metricService.metricOn;
        this.metricService.doScreenshot();
        if (this.metricService.metricOn) {
          this.metricService.metric = new Metric();
          this.messageService.info('Metryki START');
          this.metricService.startMetrics();
        } else {
          this.metricService.stopMetrics();
          this.messageService.info('Metryki STOP');
          this.metricService.add(this.metricService.metric).subscribe(() => {
            this.messageService.success('Metryka zapisana pomyślnie');
          });
        }
      } else if (event.key === 'W'.valueOf()) {
        this.metricService.metricOn = false;
        this.metricService.metric = new Metric();
        this.messageService.warning('Pomiar zatrzymany, nie zapisano metryki');
      } else if (event.key === 'R'.valueOf()) {
        this.metricService.metricOn = false;
        this.metricService.doScreenshot();
        this.metricService.stopMetrics();
        this.metricService.metric.fail = 1;
        this.messageService.info('Metryki STOP');
        this.metricService.add(this.metricService.metric).subscribe(() => {
          this.messageService.success('Metryka zapisana pomyślnie ze statusem "failed"');
        });
      }
    }
  }

  @HostListener('click', ['$event'])
  onMouseLeft(event: any) {
    this.metricService.getClickCount();
    if (this.metricService.metricOn) {
      const [xOffset, yOffset] = this.scroller.getScrollPosition();

      const currentX = event.clientX + xOffset;
      const currentY = event.clientY + yOffset;

      this.metricService.metric.distance += this.metricService.calculateDistance(
        this.metricService.lastX, this.metricService.lastY, currentX, currentY
      );
      this.metricService.lastX = currentX;
      this.metricService.lastY = currentY;
    }
  }

  @HostListener('contextmenu', ['$event'])
  onMouseRight(event: any) {
    this.metricService.getClickCount();
  }

  @HostListener('mouseup', ['$event'])
  onAuxClick(event) {
    if (event.which === 2 || event.button === 1) {
      this.metricService.getClickCount();
    }
  }

  @HostListener('window:resize', ['$event'])
  onChangeWindowsSize(event: any) {
    this.metricService.metric.resW = event.target.innerWidth;
    this.metricService.metric.resH = event.target.innerHeight;
  }

}
