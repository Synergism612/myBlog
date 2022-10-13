export default class Comment {
  id!: number;

  body!: string;

  likeCount!: string;

  father!: Comment;

  creationTime!: Date;

  modifyTime!: Date;
}
