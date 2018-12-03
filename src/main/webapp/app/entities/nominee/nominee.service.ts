import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { INominee } from 'app/shared/model/nominee.model';

type EntityResponseType = HttpResponse<INominee>;
type EntityArrayResponseType = HttpResponse<INominee[]>;

@Injectable({ providedIn: 'root' })
export class NomineeService {
    public resourceUrl = SERVER_API_URL + 'api/nominees';

    constructor(private http: HttpClient) {}

    create(nominee: INominee): Observable<EntityResponseType> {
        return this.http.post<INominee>(this.resourceUrl, nominee, { observe: 'response' });
    }

    update(nominee: INominee): Observable<EntityResponseType> {
        return this.http.put<INominee>(this.resourceUrl, nominee, { observe: 'response' });
    }

    find(id: number): Observable<EntityResponseType> {
        return this.http.get<INominee>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<INominee[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: number): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
