import { Team,TeamPageRquest, TeamInfo, TeamRequest, JoinTeamRequest, DeleteTeamRequest } from '../modules/team';
import type { R } from '../modules/R.d.ts'
import request from '../utils/request'
export const addTeam = (data: Team): Promise<R<TeamInfo>> => {
    return request({
        url: '/team/addTeam',
        method: 'post',
        data
    });
}
export const getTeamList = (params: TeamPageRquest):Promise<R<any>> => {
    return request({
        url: '/team/page/list',
        method: 'get',
        params
    })
}
export const joinTeam = (data: JoinTeamRequest):Promise<R<number>> => {
    return request({
        url: '/team/join/team',
        method: 'post',
        data
    })
}

export const quitTeam = (data: DeleteTeamRequest):Promise<R<boolean>> => {
    return request({
        url: '/team/delete',
        method: 'post',
        data
    })
}

export const createTeam = (data: TeamRequest):Promise<R<boolean>> => {
    return request({
        url: '/team/add',
        method: 'post',
        data
    })
}

export const getOneTeam = (id: any):Promise<R<Team>> => {
    return request({
        url: '/team/one/' + id,
        method: 'get',
    })
}
export const updateTeam = (data:TeamRequest):Promise<R<boolean>> => {
    return request({
        url: '/team/update' ,
        method: 'post',
        data
    })
}