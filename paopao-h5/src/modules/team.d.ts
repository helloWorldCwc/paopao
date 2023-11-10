import type { User } from './user.d.ts'
export type Team = {
  id?: number;
  teamname: string;

  maxnum: number;

  status: number;

  password: string;

  teamTags: string;

  expiredTime: Date;

  description: string;
}
export type TeamInfo = {
    
  id: number;
  isCreated: number;

  userId: number;

  teamname: string;

  maxnum: number;

  status: number;

  password: string;

  teamTags: string;

  createTime: Date;

  updateTime: Date;

  expiredTime: Date;

  description: string;

  users: User[]

}

export type TeamPageRquest = {
    current?: number;
    pageSize?: number;
    teamname?: string;

    maxNum?: number;
  
    status?: number;
  
    description?: string;
  
    teamTags?: string;
}

export type JoinTeamRequest = {
    id: number,
    password?: string
}
export type DeleteTeamRequest = {
    id: number,
    password?: string
}
export type TeamRequest = {
  id?: number;

  teamname: string;

  maxnum: number;

  status: number;

  password?: string;

  teamTags?: string;

  expiredTime: string;

  description: string;

}

