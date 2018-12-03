import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { IChoice } from 'app/shared/model/choice.model';
import { ChoiceService } from './choice.service';
import { IUser, UserService } from 'app/core';
import { ICategory } from 'app/shared/model/category.model';
import { CategoryService } from 'app/entities/category';
import { INominee } from 'app/shared/model/nominee.model';
import { NomineeService } from 'app/entities/nominee';

@Component({
    selector: 'jhi-choice-update',
    templateUrl: './choice-update.component.html'
})
export class ChoiceUpdateComponent implements OnInit {
    choice: IChoice;
    isSaving: boolean;

    users: IUser[];

    categories: ICategory[];

    nominees: INominee[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private choiceService: ChoiceService,
        private userService: UserService,
        private categoryService: CategoryService,
        private nomineeService: NomineeService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ choice }) => {
            this.choice = choice;
        });
        this.userService.query().subscribe(
            (res: HttpResponse<IUser[]>) => {
                this.users = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.categoryService.query().subscribe(
            (res: HttpResponse<ICategory[]>) => {
                this.categories = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
        this.nomineeService.query().subscribe(
            (res: HttpResponse<INominee[]>) => {
                this.nominees = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.choice.id !== undefined) {
            this.subscribeToSaveResponse(this.choiceService.update(this.choice));
        } else {
            this.subscribeToSaveResponse(this.choiceService.create(this.choice));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IChoice>>) {
        result.subscribe((res: HttpResponse<IChoice>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackCategoryById(index: number, item: ICategory) {
        return item.id;
    }

    trackNomineeById(index: number, item: INominee) {
        return item.id;
    }
}
