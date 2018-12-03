import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClapForThatSharedModule } from 'app/shared';
import { ClapForThatAdminModule } from 'app/admin/admin.module';
import {
    ChoiceComponent,
    ChoiceDetailComponent,
    ChoiceUpdateComponent,
    ChoiceDeletePopupComponent,
    ChoiceDeleteDialogComponent,
    choiceRoute,
    choicePopupRoute
} from './';

const ENTITY_STATES = [...choiceRoute, ...choicePopupRoute];

@NgModule({
    imports: [ClapForThatSharedModule, ClapForThatAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [ChoiceComponent, ChoiceDetailComponent, ChoiceUpdateComponent, ChoiceDeleteDialogComponent, ChoiceDeletePopupComponent],
    entryComponents: [ChoiceComponent, ChoiceUpdateComponent, ChoiceDeleteDialogComponent, ChoiceDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClapForThatChoiceModule {}
