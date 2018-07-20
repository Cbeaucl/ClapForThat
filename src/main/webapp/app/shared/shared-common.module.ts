import { NgModule } from '@angular/core';

import { ClapForThatSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [ClapForThatSharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [ClapForThatSharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class ClapForThatSharedCommonModule {}
