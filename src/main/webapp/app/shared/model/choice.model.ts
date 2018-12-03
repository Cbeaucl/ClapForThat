import { IUser } from 'app/core/user/user.model';
import { ICategory } from 'app/shared/model//category.model';
import { INominee } from 'app/shared/model//nominee.model';

export interface IChoice {
    id?: number;
    user?: IUser;
    category?: ICategory;
    nominee?: INominee;
}

export class Choice implements IChoice {
    constructor(public id?: number, public user?: IUser, public category?: ICategory, public nominee?: INominee) {}
}
