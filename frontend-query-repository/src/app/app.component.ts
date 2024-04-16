import { Component } from '@angular/core';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/sql/sql'
import 'codemirror/mode/markdown/markdown';
import 'codemirror/addon/display/placeholder'

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend-tester';
}
