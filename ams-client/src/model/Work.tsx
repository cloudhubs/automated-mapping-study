import { Keyword } from "./Keyword";

export class Work {
    constructor(
        public id: number,
        public doi: string,
        public authors: string,
        public documentTitle: string,
        public publicationTitle: string,
        public workAbstract: string,
        public date: string,
        public authorKeywords: Keyword[],
        public extractedKeywords: Keyword[],
        public abstractKeywords: Keyword[],
        public issn: string,
        public isbns: string
    ){}
}