import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClapForThatSharedModule } from 'app/shared';
import {
    NomineeComponent,
    NomineeDetailComponent,
    NomineeUpdateComponent,
    NomineeDeletePopupComponent,
    NomineeDeleteDialogComponent,
    nomineeRoute,
    nomineePopupRoute
} from './';

const ENTITY_STATES = [...nomineeRoute, ...nomineePopupRoute];

@NgModule({
    imports: [ClapForThatSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NomineeComponent,
        NomineeDetailComponent,
        NomineeUpdateComponent,
        NomineeDeleteDialogComponent,
        NomineeDeletePopupComponent
    ],
    entryComponents: [NomineeComponent, NomineeUpdateComponent, NomineeDeleteDialogComponent, NomineeDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClapForThatNomineeModule {}
