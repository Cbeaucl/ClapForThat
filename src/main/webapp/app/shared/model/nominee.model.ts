import { IChoice } from 'app/shared/model//choice.model';
import { ICategory } from 'app/shared/model//category.model';

export interface INominee {
    id?: number;
    name?: string;
    id?: IChoice;
    ids?: ICategory[];
}

export class Nominee implements INominee {
    constructor(public id?: number, public name?: string, public id?: IChoice, public ids?: ICategory[]) {}
}
