export interface RowAction{
    text:string,
    buttonAsText:string,
    performAction: (object: any) => void;
}