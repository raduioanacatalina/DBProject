export interface News {
  id: number;

  image?: Int8Array;

  publisher: string;

  text: string;

  publishDate: Date;

  isPinned: boolean;

  likes: number;

  comments: number;

  topics: string[];
}
