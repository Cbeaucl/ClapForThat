import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { ClapForThatSharedModule } from 'app/shared';
import { ClapForThatAdminModule } from 'app/admin/admin.module';
import {
    UserGroupComponent,
    UserGroupDetailComponent,
    UserGroupUpdateComponent,
    UserGroupDeletePopupComponent,
    UserGroupDeleteDialogComponent,
    userGroupRoute,
    userGroupPopupRoute
} from './';

const ENTITY_STATES = [...userGroupRoute, ...userGroupPopupRoute];

@NgModule({
    imports: [ClapForThatSharedModule, ClapForThatAdminModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        UserGroupComponent,
        UserGroupDetailComponent,
        UserGroupUpdateComponent,
        UserGroupDeleteDialogComponent,
        UserGroupDeletePopupComponent
    ],
    entryComponents: [UserGroupComponent, UserGroupUpdateComponent, UserGroupDeleteDialogComponent, UserGroupDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClapForThatUserGroupModule {}
