export type User = {
    id: number;

    userAccount: string;

    unionId?: string;
  
    mpOpenId?: string;  
  
    userName?: string;
  
    userAvatar?: string;
  
    userProfile?: string;
  
    tags?: string;
  
    userRole: string;
  
    createTime: String;
    userId : number

}

export type UserUpdateMyReq = {
   serName?:string
   userAvatar?:string
   userProfile?:string
}