import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpErrorResponse, HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager, JhiAlertService } from 'ng-jhipster';

import { IUserGroup } from 'app/shared/model/user-group.model';
import { Principal } from 'app/core';
import { UserGroupService } from './user-group.service';

@Component({
    selector: 'jhi-user-group',
    templateUrl: './user-group.component.html'
})
export class UserGroupComponent implements OnInit, OnDestroy {
    userGroups: IUserGroup[];
    currentAccount: any;
    eventSubscriber: Subscription;

    constructor(
        private userGroupService: UserGroupService,
        private jhiAlertService: JhiAlertService,
        private eventManager: JhiEventManager,
        private principal: Principal
    ) {}

    loadAll() {
        this.userGroupService.query().subscribe(
            (res: HttpResponse<IUserGroup[]>) => {
                this.userGroups = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    ngOnInit() {
        this.loadAll();
        this.principal.identity().then(account => {
            this.currentAccount = account;
        });
        this.registerChangeInUserGroups();
    }

    ngOnDestroy() {
        this.eventManager.destroy(this.eventSubscriber);
    }

    trackId(index: number, item: IUserGroup) {
        return item.id;
    }

    registerChangeInUserGroups() {
        this.eventSubscriber = this.eventManager.subscribe('userGroupListModification', response => this.loadAll());
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }
}
