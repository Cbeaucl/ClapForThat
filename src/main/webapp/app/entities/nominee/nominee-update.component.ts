import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiAlertService } from 'ng-jhipster';

import { INominee } from 'app/shared/model/nominee.model';
import { NomineeService } from './nominee.service';
import { IChoice } from 'app/shared/model/choice.model';
import { ChoiceService } from 'app/entities/choice';

@Component({
    selector: 'jhi-nominee-update',
    templateUrl: './nominee-update.component.html'
})
export class NomineeUpdateComponent implements OnInit {
    nominee: INominee;
    isSaving: boolean;

    choices: IChoice[];

    constructor(
        private jhiAlertService: JhiAlertService,
        private nomineeService: NomineeService,
        private choiceService: ChoiceService,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nominee }) => {
            this.nominee = nominee;
        });
        this.choiceService.query().subscribe(
            (res: HttpResponse<IChoice[]>) => {
                this.choices = res.body;
            },
            (res: HttpErrorResponse) => this.onError(res.message)
        );
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.nominee.id !== undefined) {
            this.subscribeToSaveResponse(this.nomineeService.update(this.nominee));
        } else {
            this.subscribeToSaveResponse(this.nomineeService.create(this.nominee));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INominee>>) {
        result.subscribe((res: HttpResponse<INominee>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
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

    trackChoiceById(index: number, item: IChoice) {
        return item.id;
    }
}
