import { Utente } from "./Utente";
import { Videogioco } from "./Videogioco";

export interface Recensione{
    id:number,
    titolo:string,
    data:string,
    punteggio:number,
    immagine:string,
    testo:string,
    autore:Utente,
    videogioco:Videogioco,
}