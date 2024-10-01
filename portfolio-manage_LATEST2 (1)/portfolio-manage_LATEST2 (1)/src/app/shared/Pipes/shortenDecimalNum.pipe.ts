import { Pipe, PipeTransform } from "@angular/core";

@Pipe({
   name:'shortDecimalPipe'
})

export class shoternDecimalNumPipe implements PipeTransform {

    transform(value: any, limit?:number) {
        
        if( value%1==0 ) return value;
        else{
            
             let INT_Part = value - value%1 ;
             let decimalPart = +((value%1).toString().slice(0,limit==undefined ? 4:limit))
             return INT_Part+decimalPart ;
        }


    }

}