import { NgModule, Injector } from '@angular/core';
import { CommonModule } from '@angular/common';

import { VideochatRoutingModule } from './videochat-routing.module';
import { VideoRoomComponent } from './video-room/video-room.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { StreamComponent } from './shared/components/stream/stream.component';
import { ChatComponent } from './shared/components/chat/chat.component';
import { DialogExtensionComponent } from './shared/components/dialog-extension/dialog-extension.component';
import { OpenViduVideoComponent } from './shared/components/stream/ov-video.component';
import { DialogErrorComponent } from './shared/components/dialog-error/dialog-error.component';
import { DialogChooseRoomComponent } from './shared/components/dialog-choose-room/dialog-choose-room.component';
import { WebComponentComponent } from './web-component/web-component.component';
import { ToolbarComponent } from './shared/components/toolbar/toolbar.component';
import { LinkifyPipe } from './shared/pipes/linkfy';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { MatButtonModule, MatCardModule, MatToolbarModule, MatIconModule, MatInputModule, MatFormFieldModule, MatDialogModule, MatTooltipModule, MatBadgeModule, MatGridListModule, MatSelectModule, MatOptionModule, MatProgressSpinnerModule, MatSliderModule, MatSidenavModule, MatChipsModule } from '@angular/material';

import { NgxLinkifyjsModule } from 'ngx-linkifyjs';
import { FlexLayoutModule } from '@angular/flex-layout';
import { ElementZoneStrategyFactory } from 'elements-zone-strategy';
import { OpenViduService } from './shared/services/open-vidu.service';
import { createCustomElement } from '@angular/elements';
import { ApiService } from './shared/services/api.service';

@NgModule({
  declarations: [
    VideoRoomComponent,
    DashboardComponent,
    StreamComponent,
    ChatComponent,
    DialogExtensionComponent,
    OpenViduVideoComponent,
    DialogErrorComponent,
    DialogChooseRoomComponent,
    WebComponentComponent,
    ToolbarComponent,
    LinkifyPipe
  ],
  imports: [
    CommonModule,
    VideochatRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    MatButtonModule,
    MatCardModule,
    MatToolbarModule,
    MatIconModule,
    MatInputModule,
    MatFormFieldModule,
    MatDialogModule,
    MatTooltipModule,
    MatBadgeModule,
    MatGridListModule,
    MatSelectModule,
    MatOptionModule,
    MatProgressSpinnerModule,
    MatSliderModule,
    MatSidenavModule,
    FlexLayoutModule,
    MatChipsModule,
    NgxLinkifyjsModule.forRoot()
  ],
  entryComponents: [
    DialogErrorComponent,
    WebComponentComponent,
  ],
  providers: [OpenViduService, ApiService],
})
export class VideochatModule { 
  constructor(private injector: Injector) {
    const strategyFactory = new ElementZoneStrategyFactory(WebComponentComponent, this.injector);
    const element = createCustomElement(WebComponentComponent, { injector: this.injector, strategyFactory });
    customElements.define('openvidu-webcomponent', element);
  }
}
