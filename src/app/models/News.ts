import { Utente } from "./Utente";

export interface News{
    id:number,
    titolo:string,
    categoria:string,
    immagine:string,
    data:string,
    testo:string,
    autore:Utente
}