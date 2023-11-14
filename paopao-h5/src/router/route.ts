import Index from '../pages/Index.vue'
import My from '../pages/My.vue'
import Team from '../pages/Team.vue'
import Search from '../pages/PageSearch.vue'
import BaseLayout from '../layouts/BaseLayout.vue'
import Login from '../pages/Login.vue'
import Edit from '../pages/Edit.vue'
import SearchResult from '../pages/SearchResult.vue'
import AddTeam from '../pages/AddTeam.vue'

export default  [
    { path: '/',
     component: BaseLayout,
     redirect: '/index',

      children: [
        { path: 'index', component: Index, meta: {title: '主页'},},
        { path: 'team', component: Team ,meta: {title: '队伍页'},},
        { path: 'my', component: My,meta: {title: '我的信息'}, },
        { path: 'search', component: Search, meta: {title: '信息搜索'}, },
        { path: 'edit', component: Edit , meta: {title: '信息编辑'},},
        { path: 'addTeam', component: AddTeam , meta: {title: '新增队伍'},},
        { path: 'searchResult', component: SearchResult, meta: {title: '搜索结果'}, },
      ] 
    },
    {
     path: '/login',
     component: Login,
    }
   
  ]
  