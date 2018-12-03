import { INominee } from 'app/shared/model//nominee.model';
import { IUser } from 'app/core/user/user.model';
import { ICategory } from 'app/shared/model//category.model';

export interface IChoice {
    id?: number;
    ids?: INominee[];
    id?: IUser;
    id?: ICategory;
}

export class Choice implements IChoice {
    constructor(public id?: number, public ids?: INominee[], public id?: IUser, public id?: ICategory) {}
}
