import Vue from 'vue'
import Router from 'vue-router'
import Login from '@/components/login/login'
import Frame from '@/components/frame/frame'
import User from '@/components/user/user'
import Project from '@/components/project/project'
import CreateProject from '@/components/createproject/createproject'
import WaitPass from '@/components/waitpass/waitpass'
import WaitPassDetail from '@/components/waitpassdetail/waitpassdetail'
import Labs from '@/components/labs/labs'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'Login',
      component: Login
    },
    {
      path: '/login',
      name: 'Login',
      component: Login
    },
    {
      path: '/frame',
      name: 'Frame',
      component: Frame,
      children:[
                  {
                    path: "/frame/user",
                    component:  User
                  },
                  {
                    path: "/frame/project",
                    component:  Project
                  },
                  {
                    path: "/frame/createproject",
                    component:  CreateProject
                  },
                  {
                    path: "/frame/waitpass",
                    component:  WaitPass
                  },
                  {
                    path: "/frame/waitpassdetail",
                    component:  WaitPassDetail
                  },
                  {
                    path: "/frame/labs",
                    component:  Labs
                  },

                ]
    }
  ]
})
