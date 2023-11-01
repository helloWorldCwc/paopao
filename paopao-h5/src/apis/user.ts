import { UserUpdateMyReq, User } from '../modules/user'
import request from '../utils/request'
export const updateMy = (data: UserUpdateMyReq) => {
    return request({
        url: '/user/update/my',
        method: 'post',
        data
    })
}
export const getUserInfo = (id: number) => {
    return request({
        url: '/user/get/vo',
        method: 'get',
        params: {
            id
        }
    })
}
export const getCurrentUser = () => {
    return request({
        url: '/user/get/login',
        method: 'get',
    })
}

export const getRecommendUsers = () => {
    return request({
        url: '/user/recommend',
        method: 'get',
    })
}