import { Injectable, ComponentFactoryResolver, ApplicationRef, Injector, EmbeddedViewRef } from '@angular/core';
import {CodeMirrorFullscreenComponent} from "../shared/code-mirror-fullscreen/code-mirror-fullscreen.component";
import {NewWindowComponent} from "./new-window/new-window.component";

@Injectable({
  providedIn: 'root'
})
export class WindowService {
  constructor(
      private componentFactoryResolver: ComponentFactoryResolver,
      private appRef: ApplicationRef,
      private injector: Injector
  ) {}

  openComponentInNewWindow(content: string): void {
    // Create a new window
    let newWindow = window.open('', '_blank', 'width=800,height=600');

    // Create an Angular component factory for CodeMirrorFullscreenComponent
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(NewWindowComponent);

    // Create a component instance
    const componentRef = componentFactory.create(this.injector);

    // Set the content property of the component
    componentRef.instance.setContent(content);

    // Attach the component to the application's view so change detection works
    this.appRef.attachView(componentRef.hostView);

    // Get the component's DOM element
    const componentRootNode = (componentRef.hostView as EmbeddedViewRef<any>).rootNodes[0] as HTMLElement;

    // Append the component's DOM element to the new window
    // @ts-ignore
    newWindow.document.body.appendChild(componentRootNode);

    // Handle cleanup when the new window is closed
    // @ts-ignore
    newWindow.onbeforeunload = () => {
      this.appRef.detachView(componentRef.hostView);
      componentRef.destroy();
    };
  }
}
