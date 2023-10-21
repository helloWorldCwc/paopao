import Index from '../pages/Index.vue'
import My from '../pages/My.vue'
import Team from '../pages/Team.vue'
import Search from '../pages/PageSearch.vue'
import BaseLayout from '../layouts/BaseLayout.vue'
import Login from '../pages/Login.vue'
import Edit from '../pages/Edit.vue'
import SearchResult from '../pages/SearchResult.vue'

export default  [
    { path: '/',
     component: BaseLayout,
     redirect: '/index',
      children: [
        { path: 'index', component: Index },
        { path: 'team', component: Team },
        { path: 'my', component: My },
        { path: 'search', component: Search },
        { path: 'edit', component: Edit },
        { path: 'searchResult', component: SearchResult },
      ] 
    },
    {
     path: '/login',
     component: Login,
    }
   
  ]
  