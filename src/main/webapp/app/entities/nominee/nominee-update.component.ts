import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { INominee } from 'app/shared/model/nominee.model';
import { NomineeService } from './nominee.service';

@Component({
    selector: 'jhi-nominee-update',
    templateUrl: './nominee-update.component.html'
})
export class NomineeUpdateComponent implements OnInit {
    nominee: INominee;
    isSaving: boolean;

    constructor(private nomineeService: NomineeService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nominee }) => {
            this.nominee = nominee;
        });
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
}
