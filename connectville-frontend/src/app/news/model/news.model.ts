export interface News {
  id: number;

  image?: String;

  publisher: string;

  text: string;

  publishDate: Date;

  pinned: boolean;

  likes: Array<UserLike>;

  comments: Array<UserComment>;

  topics: string[];

  cop: string;

  likedByUser: boolean;
}

export interface UserComment {
  id: number;
  userId: number;
  text: string;
}

export interface UserLike {
  id: number;
  userId: number;
  pressed: boolean;
}
