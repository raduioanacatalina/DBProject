export interface User {
   
    id: number;

    image: Int8Array;
   
    publisher: string;
    
    text: string;

    publishDate: Date;
  
    isPinned: boolean;
   
    likes: number;

    comments: number;

    topics: Set<String>;
}