import {Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild} from '@angular/core';
import {TrackService} from '../../../services/track.service';

@Component({
  selector: 'app-file-upload',
  templateUrl: './file-upload.component.html'
})
export class FileUploadComponent implements OnInit {

  loading: boolean = false;
  file!: File;

  @ViewChild('myUpload')
  myInputVariable!: ElementRef;

  @Output() uploaded: EventEmitter<string> = new EventEmitter();

  constructor(private trackService: TrackService) {
  }

  ngOnInit(): void {
  }

  onChange(event: any) {
    this.file = event.target.files[0];
  }

  onUpload() {
    this.loading = !this.loading;

    this.trackService.upload(this.file).subscribe((data: any) => {
      this.loading = false;
      this.myInputVariable.nativeElement.value = '';
      this.uploaded.emit("complete");
    });
  }
}
