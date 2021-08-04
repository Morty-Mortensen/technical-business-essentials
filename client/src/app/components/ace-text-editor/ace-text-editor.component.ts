import {AfterViewInit, Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import * as ace from 'ace-builds';
// language package, choose your own
import 'ace-builds/src-noconflict/mode-javascript';
// ui-theme package
import 'ace-builds/src-noconflict/theme-github';
import 'ace-builds/src-noconflict/ext-language_tools';
import 'ace-builds/src-noconflict/ext-beautify';
import {HttpClient} from "@angular/common/http";
import {SnackbarService} from "../../services/utils/snackbar.service";
import {CompiledCode} from "../../models/course";

const THEME = 'ace/theme/github';
const LANG = 'ace/mode/javascript';

@Component({
  selector: 'app-ace-text-editor',
  templateUrl: './ace-text-editor.component.html',
  styleUrls: ['./ace-text-editor.component.scss']
})
export class AceTextEditorComponent implements OnInit, AfterViewInit {

  @Input() sampleCode: string = "console.log('Hello World!');";
  @Input() codeLanguage: string = 'nodejs';
  @Input() codeLanguageVersion: string = '3';

  @ViewChild('codeEditor') codeEditorElmRef!: ElementRef;
  private codeEditor!: ace.Ace.Editor;
  private editorBeautify: any;

  public code: string = '';

  constructor(private http: HttpClient, private snackBar: SnackbarService) {

  }

  ngOnInit() {
  }

  ngAfterViewInit() {
    const element = this.codeEditorElmRef.nativeElement;
    const editorOptions: Partial<ace.Ace.EditorOptions> = this.getEditorOptions();

    this.codeEditor = ace.edit(element, editorOptions);
    this.codeEditor.setTheme(THEME);
    this.codeEditor.getSession().setMode(LANG);
    this.codeEditor.setValue(this.sampleCode)
    this.codeEditor.setShowFoldWidgets(true); // for the scope fold feature
    this.editorBeautify = ace.require(`ace/ext/beautify`);
  }

  public submitCode() {
    this.code = this.codeEditor.getValue();

    this.http.post("http://localhost:8080/JDoodle", {
      script: this.code,
      language: this.codeLanguage,
      versionIndex: this.codeLanguageVersion
    }).subscribe(res => {
      this.snackBar.show('Compiled output: ' + (res as CompiledCode).output)
      // console.log("Success")
      // console.log(res);
    }, err => {
      console.log("Error :(")
      console.log(err)
    });
  }

  // Makes the code look sweet!
  public beautifyContent() {
    if (this.codeEditor && this.editorBeautify) {
      const session = this.codeEditor.getSession();
      this.editorBeautify.beautify(session);
    }
  }

  // Add options (including auto complete)
  private getEditorOptions(): Partial<ace.Ace.EditorOptions> & { enableBasicAutocompletion?: boolean; } {
    const basicEditorOptions: Partial<ace.Ace.EditorOptions> = {
      highlightActiveLine: true,
      minLines: 14,
      maxLines: Infinity,
    };

    const extraEditorOptions = {
      enableBasicAutocompletion: true
    };
    const mergedOptions = Object.assign(basicEditorOptions, extraEditorOptions);
    return mergedOptions;
  }

}