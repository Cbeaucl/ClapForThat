import { IChoice } from 'app/shared/model//choice.model';
import { IEvent } from 'app/shared/model//event.model';
import { INominee } from 'app/shared/model//nominee.model';

export interface ICategory {
    id?: number;
    name?: string;
    description?: string;
    completed?: boolean;
    choices?: IChoice[];
    id?: IEvent;
    id?: INominee;
}

export class Category implements ICategory {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public completed?: boolean,
        public choices?: IChoice[],
        public id?: IEvent,
        public id?: INominee
    ) {
        this.completed = this.completed || false;
    }
}
