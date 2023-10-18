import Index from '../pages/Index.vue'
import My from '../pages/My.vue'
import Team from '../pages/Team.vue'
import Search from '../pages/PageSearch.vue'
import BaseLayout from '../layouts/BaseLayout.vue'
import Login from '../pages/Login.vue'
export default  [
    { path: '/',
     component: BaseLayout,
     redirect: '/index',
      children: [
        { path: 'index', component: Index },
        { path: 'team', component: Team },
        { path: 'my', component: My },
        { path: 'search', component: Search },
      ] 
    },
    {
     path: '/login',
     component: Login,
    }
   
  ]
  