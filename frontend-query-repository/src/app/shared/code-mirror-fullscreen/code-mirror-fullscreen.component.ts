import {Component, Input, ViewEncapsulation} from '@angular/core';

@Component({
  selector: 'app-code-mirror-fullscreen',
  templateUrl: './code-mirror-fullscreen.component.html',
  styleUrls: ['./code-mirror-fullscreen.component.css'],
  encapsulation: ViewEncapsulation.None,

})
export class CodeMirrorFullscreenComponent{


  @Input() content: string = "";


  public closeFullScreen(cm:any) {
    cm.setOption("fullScreen",false);
  }

  public setFullScreen(cm:any){
    cm.setOption("fullScreen",!cm.getOption("fullScreen"));
  }


  public setContent(content:string){
    this.content = content;
  }

  protected readonly Infinity = Infinity;
}
