import { Component, OnInit } from '@angular/core';
import {PositionsService} from '../services/positions.service';
import {Position} from './model/position';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-positions',
  templateUrl: './positions.component.html',
  styleUrls: ['./positions.component.css']
})
export class PositionsComponent implements OnInit {

  position: Position;
  closeResult: string;

  constructor(private positionsService: PositionsService,
              private modalService: NgbModal) { }

  open(content) {
    this.modalService.open(content, {ariaLabelledBy: 'modal-basic-title'}).result.then((result) => {}, (reason) => {});
  }

  addNew() {
    this.positionsService.addNew(this.position).subscribe(success => {});
  }

  ngOnInit() {
   this.position = new Position();
  }
}
