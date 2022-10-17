export interface News {
  id: number;

  image?: Int8Array;

  publisher: string;

  text: string;

  publishDate: Date;

  isPinned: boolean;

  likes: Set<UserLike>;

  comments: Set<UserComment>;

  topics: string[];
}

export interface UserComment {
  id: number;
  userId: number;
  text: string;
}

export interface UserLike {
  id: number;
  userId: number;
}
