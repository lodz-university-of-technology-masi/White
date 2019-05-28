export class Metric {
  ip: string;
  browser: string;
  resW: number;
  resH: number;
  mouseClicks: number;
  distance: number;
  time: number;
  fail: number;
  error: number;

  constructor() {
    this.ip = '';
    this.browser = '';
    this.resH = 0;
    this.resW = 0;
    this.fail = 0;
    this.error = 0;
    this.mouseClicks = 0;
    this.distance = 0;
    this.time = 0;
  }
}
