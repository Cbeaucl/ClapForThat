import { IChoice } from 'app/shared/model//choice.model';
import { ICategory } from 'app/shared/model//category.model';

export interface INominee {
    id?: number;
    name?: string;
    choices?: IChoice[];
    ids?: ICategory[];
}

export class Nominee implements INominee {
    constructor(public id?: number, public name?: string, public choices?: IChoice[], public ids?: ICategory[]) {}
}
