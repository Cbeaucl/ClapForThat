import { IChoice } from 'app/shared/model//choice.model';
import { IEvent } from 'app/shared/model//event.model';
import { INominee } from 'app/shared/model//nominee.model';

export interface ICategory {
    id?: number;
    name?: string;
    description?: string;
    completed?: boolean;
    choices?: IChoice[];
    event?: IEvent;
    nominee?: INominee;
}

export class Category implements ICategory {
    constructor(
        public id?: number,
        public name?: string,
        public description?: string,
        public completed?: boolean,
        public choices?: IChoice[],
        public event?: IEvent,
        public nominee?: INominee
    ) {
        this.completed = this.completed || false;
    }
}
