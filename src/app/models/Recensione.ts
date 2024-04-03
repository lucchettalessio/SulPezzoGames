import { Utente } from "./Utente";
import { Videogioco } from "./Videogioco";

export interface Recensione{
    id:number,
    titolo:string,
    data:string,
    punteggio:number,
    immagine:string,
    immagine2:string,
    testo:string,
    autore:Utente,
    videogioco:Videogioco,
}