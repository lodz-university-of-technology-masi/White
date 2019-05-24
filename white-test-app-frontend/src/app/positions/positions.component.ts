import {Component, OnInit} from '@angular/core';
import {PositionsService} from '../services/positions.service';
import {Position} from './model/position';
import {NgbModal} from '@ng-bootstrap/ng-bootstrap';
import {MessageService} from '../services/message.service';

@Component({
  selector: 'app-positions',
  templateUrl: './positions.component.html',
  styleUrls: ['./positions.component.css']
})
export class PositionsComponent implements OnInit {

  position: Position;
  positions: Position[];
  closeResult: string;
  readonly color = 'warn';

  constructor(private positionsService: PositionsService,
              private modalService: NgbModal,
              private messageService: MessageService) {
  }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {
    }, (reason) => {
    });
  }

  change(positionId) {
    this.positionsService.changeStatus(positionId).subscribe(success => {
      this.messageService.success('Sukces');
      this.loadPositions();
    }, error => {
      this.messageService.error('Błąd');
    });
  }

  addNew() {
    this.positionsService.addNew(this.position).subscribe(success => {
      this.messageService.success('Sukces');
      this.loadPositions();
    }, error => {
      this.messageService.error('Błąd');
    });
  }

  ngOnInit() {
    this.position = new Position();
    this.loadPositions();
  }

  loadPositions() {
    this.positionsService.getAllPositionsUnfiltered().subscribe(t => {
      this.positions = t;
    });
  }
}
