import { Component } from '@angular/core';

@Component({
  selector: 'app-new-window',
  templateUrl: './new-window.component.html',
  styleUrls: ['./new-window.component.css']
})
export class NewWindowComponent {
  content:string ="";


  public setContent(content:string){
    this.content = content;
  }

 public syntaxHighlight(json:string) {
    if (typeof json != "string") {
      json = JSON.stringify(json, null, "\t");
    }

    json = json
        .replace(/&/g, "&amp;")
        .replace(/</g, "&lt;")
        .replace(/>/g, "&gt;");

    return json.replace(
        /("(\\u[a-zA-Z0-9]{4}|\\[^u]|[^\\"])*"(\s*:)?|\b(true|false|null)\b|-?\d+(?:\.\d*)?(?:[eE][+\-]?\d+)?)/g,
        function(match) {
          var cls = "number";
          if (/^"/.test(match)) {
            if (/:$/.test(match)) {
              cls = "key";
            } else {
              cls = "string";
            }
          } else if (/true|false/.test(match)) {
            cls = "boolean";
          } else if (/null/.test(match)) {
            cls = "null";
          }
          return '<span class="' + cls + '">' + match + "</span>";
        }
    );
  }




}
