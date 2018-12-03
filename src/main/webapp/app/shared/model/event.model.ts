import { Moment } from 'moment';
import { IUserGroup } from 'app/shared/model//user-group.model';
import { ICategory } from 'app/shared/model//category.model';

export interface IEvent {
    id?: number;
    startTime?: Moment;
    name?: string;
    group?: IUserGroup;
    categories?: ICategory[];
}

export class Event implements IEvent {
    constructor(
        public id?: number,
        public startTime?: Moment,
        public name?: string,
        public group?: IUserGroup,
        public categories?: ICategory[]
    ) {}
}
