import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IUserGroup } from 'app/shared/model/user-group.model';
import { UserGroupService } from './user-group.service';
import { IUser, UserService } from 'app/core';

@Component({
    selector: 'jhi-user-group-update',
    templateUrl: './user-group-update.component.html'
})
export class UserGroupUpdateComponent implements OnInit {
    userGroup: IUserGroup;
    isSaving: boolean;

    users: IUser[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private userGroupService: UserGroupService,
        private userService: UserService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ userGroup }) => {
            this.userGroup = userGroup;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.userGroup.id !== undefined) {
            this.subscribeToSaveResponse(this.userGroupService.update(this.userGroup));
        } else {
            this.subscribeToSaveResponse(this.userGroupService.create(this.userGroup));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IUserGroup>>) {
        result.subscribe((res: HttpResponse<IUserGroup>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }

    private onError(errorMessage: string) {
        this.jhiAlertService.error(errorMessage, null, null);
    }

    trackUserById(index: number, item: IUser) {
        return item.id;
    }

    getSelected(selectedVals: Array<any>, option: any) {
        if (selectedVals) {
            for (let i = 0; i < selectedVals.length; i++) {
                if (option.id === selectedVals[i].id) {
                    return selectedVals[i];
                }
            }
        }
        return option;
    }
}
