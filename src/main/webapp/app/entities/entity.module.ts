import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { ClapForThatUserGroupModule } from './user-group/user-group.module';
import { ClapForThatEventModule } from './event/event.module';
import { ClapForThatCategoryModule } from './category/category.module';
import { ClapForThatNomineeModule } from './nominee/nominee.module';
import { ClapForThatChoiceModule } from './choice/choice.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    // prettier-ignore
    imports: [
        ClapForThatUserGroupModule,
        ClapForThatEventModule,
        ClapForThatCategoryModule,
        ClapForThatNomineeModule,
        ClapForThatChoiceModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class ClapForThatEntityModule {}
