import { IUser } from 'app/core/user/user.model';
import { IEvent } from 'app/shared/model//event.model';

export interface IUserGroup {
    id?: number;
    name?: string;
    active?: boolean;
    ids?: IUser[];
    ids?: IEvent[];
}

export class UserGroup implements IUserGroup {
    constructor(public id?: number, public name?: string, public active?: boolean, public ids?: IUser[], public ids?: IEvent[]) {
        this.active = this.active || false;
    }
}
